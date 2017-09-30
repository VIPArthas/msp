package com.wh.work;

import com.wh.constants.Constants;
import com.wh.entity.*;
import com.wh.pojo.MemberInfo;
import com.wh.service.rgpp.PlatformService;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.service.wmh.WmhUserService;
import com.wh.service.wv.WvService;
import com.wh.service.xlwapp.UserService;
import com.wh.util.WebUtil;
import com.wh.util.WeiXinPlatformUtil;
import javax.annotation.PostConstruct;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.*;

@Component
public class TokenPlatformJob {
	private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PlatformService platformService;
    @Autowired
    private WchatuserPlatService wchatuserPlatService;
 
  
    @Autowired
	WvService wvService;
    @Autowired
    private UserService userService;
 
    @Autowired
	private WmhUserService wmhUserService;
    @PostConstruct
    public void init(){
    	if(this.platformService!=null){
    		WeiXinPlatformUtil.platformService = this.platformService;
    	}
    	WebUtil.userService = userService;
		WebUtil.wmhUserService=wmhUserService;
    }
    public void run() {
        log.info("微信AccessToken维护线程检测中...");
        try{
           this.platformService.updateAccessTokenByNetwork();
        }catch (Exception e){
            log.error("{}",e);
        }

        try{
//            this.wchatuserPlatService.updateMemberInfo();


        }catch (Exception e){
            log.error("{}",e);
        }
    }


//    @Scheduled(cron = "0 0 12 * * ?")//每天中午12点触发 寒假工签到提醒
//    void doSomethingWith(){
//        log.info("定时任务开始......");
//        long begin = System.currentTimeMillis();
//
//
//        long end = System.currentTimeMillis();
//        log.info("定时任务结束，共耗时：[" + (end - begin) / 1000 + "]秒");
//        Map<String, Object> temMap = new HashMap<>();
//        try {
//            List<WvSignWarn> list=wvSignWarnService.searchAll();
//            if(list.size()>0){
//                for(int i=0;i<list.size();i++){
//                    WvSignWarn wvSignWarn=list.get(i);
//					if(!StringUtils.isEmpty(wvSignWarn)&& wvSignWarn.getStatus()==0){
//						temMap.put("toOpenId", wvSignWarn.getOpenId());
//						temMap.put("url", PropertiesUtil.getPropertie("WEB-INF/classes/config.properties", "base_server")+"/wv/hjg/wx/toQianDao.htm");
//						WeiXinPlatformUtil.sendTemplate(temMap,"3","1"); //发送模板消息
//					}
//                }
//            }
//        } catch (Exception e) {
//        	log.error("异常日志：", e);
//        }
//    }
    
  
//	@Scheduled(cron = "0 0 8-22/2 * * ?") //每天8点到晚上10点之内每2小时触发一次 每次随机增加1到5个人   寒假工首页小喇叭显示的已经报名的人数 伪造数据
//	void doSomething(){
////		int number = (int)(3*Math.random()) + 1; //随机生成1-3之内的数子
//		try {
//			WvNumber wvNumber=wvService.selectNumber();
//			if(null==wvNumber){
//				wvNumber=new WvNumber();
//				wvNumber.setId(UUIDUtil.getUUID());
//				wvNumber.setNumber(121);
//				wvService.insertNumber(wvNumber);
//			}else {
//				wvNumber.setNumber(wvNumber.getNumber()+1);
//				wvService.updateNumber(wvNumber);
//			}
//		} catch (Exception e) {
//			log.error("异常日志：", e);
//		}
//	}

	/**
	 * 定时更新微信用户表信息，以后稳定了，可以改成每星期更新一次，现在是每天更新一次
	 * 具体做法是：
	 * 1、先去微信服务器查询关注的总用户数，拿到所以的openid
	 * 2、去数据库中更新，如果数据库中该公众号下的openid不在此类，则认为未关注，更新状态（排除 取消关注的用户）
	 * 3、循环所有的openid，去微信服务器查询最新的用户信息，然后保存到数据库（如果有，就更新。没有就插入）
	 */
	@Scheduled(cron = "0 20 2 * * ?")
	public void updateWchatUser(){
		int count = 0;
		String nextOpenId = "";
		List<String> openIds = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			JSONObject jsonObject = WeiXinPlatformUtil.getGroupUser(Constants.platformId, nextOpenId);
			if (jsonObject.containsKey("errcode")) {
				return;
			}
			count = jsonObject.getInt("count");
			nextOpenId = jsonObject.getString("next_openid");
			List<String> list2 = JSONArray.toList(jsonObject.getJSONObject("data").getJSONArray("openid"), new Object(), new JsonConfig());
			openIds.addAll(list2);
			if (count < 10000) {
				break;
			}
		}
		//为防止意外，暂时设置查询出来的数量为20000 以上时 再去更新数据库状态。(此段代码不一定每天执行)
		if (openIds.size() > 20000) {
			TbWchatuser wchatuser = new TbWchatuser();
			wchatuser.setOpenIds(openIds);
			wchatuser.setPlatformId(Constants.platformId);
			wchatuser.setStatus(0);
			wchatuserPlatService.updateStatusByOpenId(wchatuser);
		}

		//处理数据库中没有存的用户
		for (String openId : openIds) {
			MemberInfo memberInfo = WeiXinPlatformUtil.getMemberInfo(openId, Constants.platformId);
			TbWchatuser tbWchatuser = new TbWchatuser();
			tbWchatuser.setPlatformId(Constants.platformId);
			tbWchatuser.setOpenid(openId);
			tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
			tbWchatuser.setNickName(memberInfo.getNickname());
			tbWchatuser.setSource("-1");
			tbWchatuser.setStatus(1);
			tbWchatuser.setCreatetime(new Date(memberInfo.getSubscribeTime() * 1000));
			wchatuserPlatService.saveOrAddBySns(tbWchatuser);
//				TbWchatuser tbWchatuser = wchatuserPlatService.findWchatUserByOpenId(openId);
//				//有关注用户，数据库没有记录
//				if (null == tbWchatuser || StringUtils.isEmpty(tbWchatuser.getId())) {
//					MemberInfo memberInfo = WeiXinPlatformUtil.getMemberInfo(openId, "1");
//					tbWchatuser = new TbWchatuser();
//					tbWchatuser.setPlatformId("1");
//					tbWchatuser.setOpenid(openId);
//					tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
//					tbWchatuser.setNickName(memberInfo.getNickname());
//					tbWchatuser.setSource("-1");
//					tbWchatuser.setStatus(1);
//					tbWchatuser.setAddressProvince(memberInfo.getProvince());
//					tbWchatuser.setAddressCity(memberInfo.getCity());
//					tbWchatuser.setCreatetime(new Date(memberInfo.getSubscribeTime() * 1000));
//					wchatuserPlatService.add(tbWchatuser);
//				}
		}




	}
	
	
	
}
