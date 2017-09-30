/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageServiceImpl.java
 * 
 * 描  述：
 * 
 * [变更履历]
 * Version   变更日         		部署              作者           变更内容
 * -----------------------------------------------------------------------------
 * V1.00     2017年3月7日   		jh   	 wd     create
 * -----------------------------------------------------------------------------
 *
 *
 * Copyright (c) 2017  	jh All Rights Reserved.
 *┌─────────────────────────────────────────────────—────┐
 *│ 版权声明                               	jh      	│
 *└──────────────────────────────—————————————————————───┘
 */

package com.wh.service.wmh.impl;

import java.util.*;

import com.wh.constants.Constants;
import com.wh.controller.common.BaseController;
import com.wh.dao.wmh.WmhUserMapper;
import com.wh.dao.xlwapp.UserMapper;
import com.wh.entity.*;
import com.wh.service.base.impl.BaseServiceImpl;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.service.wmh.WmhUserService;
import com.wh.service.xlwapp.SmsService;
import com.wh.service.xlwapp.UserService;
import com.wh.util.*;
import com.wh.util.wxpay.Log;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.wh.dao.wmh.WmhMessageMapper;
import com.wh.dao.wmh.WmhTagUseMapper;
import com.wh.dao.wmh.WmhUserTagMapper;
import com.wh.service.wmh.WmhMessageService;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消息推送
 * <p/>
 * <p>
 * <a href="WmhMessageServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:">wd</a>
 * @version Revision: 1.0  Date: 2017年3月7日 下午2:00:13
 */
@Service
public class WmhMessageServiceImpl extends BaseServiceImpl<WmhMessage, Integer> implements WmhMessageService {
    @Autowired
    WmhMessageMapper wmhMessageMapper;

    @Resource
	public void setBaseDAO(WmhMessageMapper wmhMessageMapper) {
		this.wmhMessageMapper = wmhMessageMapper;
		this.baseDAO = wmhMessageMapper;
	}
    @Autowired
    WmhUserTagMapper wmhUserTagMapper;

    @Autowired
    WmhTagUseMapper wmhTagUseMapper;
    @Autowired
    private SmsService smsService;
    @Autowired
    private WchatuserPlatService wchatuserPlatService;
    @Autowired
    private WmhUserService wmhUserService;
    @Autowired
    private WmhUserMapper wmhUserMapper;


    /**
     * 根据标签名称查询标签ID和具有该标签的用户数量
     */
    @Override
    public Map<String, Object> getTagIdAndUserCount(String tagName) {
        Map<String, Object> map = new HashMap<String, Object>();
        if ("全部".equals(tagName)) {
            map = wmhUserTagMapper.getAllUserCount();
        } else {
            map = wmhUserTagMapper.getTagIdAndUserCount(tagName);
        }

        long count = (long) map.get("count");
        if (count == 0) {
            map.put("code", -1);
            return map;
        }
        map.put("code", 1);
        return map;
    }

    /**
     * 推送消息的方法
     */
    @Override
    public Integer pushMessageMethod(HttpServletRequest httpServletRequest, WmhMessage wmhMessage) {
        Integer sendPersonNumber=0;
        if (null != wmhMessage) {
            if (null != wmhMessage.getTemplateType() && wmhMessage.getTemplateType() == WmhMessageService.TEMPLATE_TYPE_HYTZ) {  //会议通知
                String tagId = wmhMessage.getToUser();
                String userNameArray =wmhMessage.getUserNameArray();
                /*if (org.springframework.util.StringUtils.isEmpty(tagId)) {
                    return 0;
                }*/
                if (StringUtil.isEmpty(tagId)&&StringUtil.isEmpty(userNameArray)) {
                	return 0;
				}
                
                List<WmhUser> userList = new ArrayList<>();
                try {
                    if (tagId.equals("all")) {
                        userList = wmhUserMapper.searchAllUser();
                    } else {
                        if (!org.springframework.util.StringUtils.isEmpty(tagId)) { //多个标签
                            if (tagId.contains(",")) {
                                String[] b = tagId.split(",");
                                if (b.length > 0) {
                                    for (int i = 0; i < b.length; i++) {
                                        tagId = b[i];
                                        List<WmhUser> list = wmhUserService.selectUserByTagId(tagId);
                                        userList.addAll(list);
                                        WmhTagUse wmhTagUse = new WmhTagUse();
                                        wmhTagUse.setId(tagId);
                                        wmhTagUse.setLastUseTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                        wmhTagUseMapper.updateTagNumberById(wmhTagUse);
                                    }
                                }
                            } else { //一个标签
                                List<WmhUser> list = wmhUserService.selectUserByTagId(tagId);
                                userList.addAll(list);
                                WmhTagUse wmhTagUse = new WmhTagUse();
                                wmhTagUse.setId(tagId);
                                wmhTagUse.setLastUseTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                wmhTagUseMapper.updateTagNumberById(wmhTagUse);
                            }
                        }
                    }

                    if (StringUtil.isNotEmpty(userNameArray)) {
						if (userNameArray.contains(",")) {
							String[] nameArray=userNameArray.split(",");
							if (nameArray.length>0) {
								for (int i = 0; i < nameArray.length; i++) {
									List<WmhUser> userListByname =wmhUserService.getUserByUserName(nameArray[i]);
									userList.addAll(userListByname);
								}
							}
						}
					}
                    
                    
                    if (null != userList && userList.size() > 0) {
                    	
                        Set<WmhUser> userSet = new HashSet<>();
                        userSet.addAll(userList);//数据去重    需重写hashcode,equals
                        for (WmhUser u : userSet) {
//                            String uId=u.getId();
//                            User u=userService.selectUserById(uId);
                            if (null != u) {
                                int wxSend = wmhMessage.getWxSend();
                                int smsSend = wmhMessage.getSmsSend();
                                int mailSend = wmhMessage.getMailSend();
                                String msgContent = "";
                                String phone = u.getPhone();
                                msgContent = "您有一个会议需要参加,会议名称：" + wmhMessage.getParm2() + " 会议时间:" + wmhMessage.getParm3() + " 会议地点：" + wmhMessage.getParm4()
                                        + " 会议介绍：" + wmhMessage.getParm5();
                                if (wxSend == 1) {  //微信发送
                                    sendWxMethod(u.getId(), wmhMessage);
                                }
                                if (smsSend == 1) { //短信发送
                                    if(!StringUtils.isEmpty(phone)){
                                        sendSmsMethod(httpServletRequest, phone, msgContent);
                                    }

                                }
                                if (mailSend == 1) {  //邮件发送
                                    String mail=u.getMail();
                                    if(!StringUtils.isEmpty(mail)){
                                        sendMailMethod(mail,u.getNickName(),"会议通知",msgContent);
                                    }
                                }
                            }
                        }
                    }
                    wmhMessageMapper.insertSelective(wmhMessage); //保存推送的消息
                } catch (Exception e) {

                }
                sendPersonNumber=userList.size();

            } else if (null != wmhMessage.getTemplateType() && wmhMessage.getTemplateType() == WmhMessageService.TEMPLATE_TYPE_XZFFTZ) {//薪资发放
                sendPersonNumber=1;
                String uId = wmhMessage.getToUser();
                WmhUser u = wmhUserService.selectUserById(uId);
                if (null != u) {
                    int wxSend = wmhMessage.getWxSend();
                    int smsSend = wmhMessage.getSmsSend();
                    int mailSend = wmhMessage.getMailSend();
                    String msgContent = "";
                    String phone = u.getPhone();
                    msgContent = "您好，您的" + wmhMessage.getParm2() + "工资已经发放,应发工资" + wmhMessage.getParm3() + "元,实发工资" + wmhMessage.getParm4() + "元";
                    if (wxSend == 1) {  //微信发送
                        sendWxMethod(u.getId(), wmhMessage);
                    }
                    if (smsSend == 1) { //短信发送
                        if(!StringUtils.isEmpty(phone)){
                            sendSmsMethod(httpServletRequest, phone, msgContent);
                        }
                    }
                    if (mailSend == 1) {  //邮件发送
                        String mail=u.getMail();
                        if(!StringUtils.isEmpty(mail)){
                            sendMailMethod(mail,u.getNickName(),"薪资发放",msgContent);
                        }

                    }
                }
                wmhMessageMapper.insertSelective(wmhMessage); //保存推送的消息
            }else if(null != wmhMessage.getTemplateType() && wmhMessage.getTemplateType() == WmhMessageService.TEMPLATE_TYPE_ZSXGRZTZ){//周师修改日志通知
            	sendPersonNumber=1;
            	String phone = wmhMessage.getToUser();
            	WmhUser user = new WmhUser();
            	user.setPhone(phone);
            	try {
					List<WmhUser> listUsers = wmhUserService.selectUserByPhone(user);
					if(CollectionUtils.isEmpty(listUsers)){
						return -1;
					}
					WmhUser wmhUser = listUsers.get(0);
					List<TbWchatuser> TbWchatuseres = wchatuserPlatService.queryByUserId(wmhUser.getId());
					if(CollectionUtils.isEmpty(TbWchatuseres)){
						return -1;
					}
					TbWchatuser tbWchatuser = TbWchatuseres.get(0);
					if(StringUtils.isEmpty(tbWchatuser.getOpenid())){
						return -1;
					}
                    // 默认微信发送
                   sendWxMethod(wmhUser.getId(), wmhMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	
            }
        }
        return sendPersonNumber;
    }

    //发送短信调用的方法
    public void sendSmsMethod(HttpServletRequest request, String phone, String content) {
//            JSONObject object = new JSONObject();
        try {
            if (com.taobao.api.internal.util.StringUtils.isEmpty(phone)) {
//                object.put("code", -1);
//                object.put("msg", "手机号不能为空(phone)");
//                    WebUtil.write(response, object.toString());
//                log.error("异常日志：", "手机号不能为空");
                return;
            } else if (!StringUtil.isMobile(phone)) {
//                object.put("code", -1);
//                object.put("msg", "手机号格式不正确(phone)");
//                WebUtil.write(response, object.toString());
//                log.error("异常日志：", "手机号格式不正确");
                return;
            }

            if (com.taobao.api.internal.util.StringUtils.isEmpty(content)) {
//                object.put("code", -1);
//                object.put("msg", "内容不能为空(content)");
//                WebUtil.write(response, object.toString());
//                log.error("异常日志：", "短信内容不能为空");
                return;
            }
            String ip = WebUtil.getIp(request);
            SmsVerify smsVerify = new SmsVerify("", phone, content, "", null, ip, null);
            smsVerify.setOperateIp(ip);
            smsVerify = new MessageCode().sendSMS(smsVerify);
            if (smsVerify != null) {//发送成功
                smsService.saveSms(smsVerify);
//                    object.put("code", 0);
//                    object.put("msg", smsVerify.getReturnStatus());
//                    WebUtil.write(response, object.toString());
//                log.error("发送短信：", "success");
            }

        } catch (Exception e) {
//            log.error("异常日志：", e);
        }
    }

    //发送邮件的方法
    /**
     *
     * @param receiveMailAccount  收件人邮箱
     * @param userName   收件人名称
     * @param mailTitle  邮件主题
     * @param mailContent  邮件内容
     */
    public void sendMailMethod(String receiveMailAccount,String userName,String mailTitle,String  mailContent) {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", com.wh.util.Constants.MY_EMAIL_SMTP_HOST);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        try {
            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, com.wh.util.Constants.MY_EMAIL_ACCOUNT, receiveMailAccount, userName,mailTitle,mailContent);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            transport.connect(com.wh.util.Constants.MY_EMAIL_ACCOUNT, com.wh.util.Constants.MY_EMAIL_PASSWORD);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //微信公众平台推送的方法
    public void sendWxMethod(String userId, WmhMessage wmhMessage) {
        //如果用户关注了公众号，那么就向他的微信推送一条任务信息
        TbWchatuser tbWchatuser = wchatuserPlatService.searchByUserId(userId);
        if (null != tbWchatuser && !com.taobao.api.internal.util.StringUtils.isEmpty(tbWchatuser.getOpenid())) { //说明关注了公众号
            String openId = tbWchatuser.getOpenid();
            //调用模板消息
            Map<String, Object> temMap = new HashMap<>();
            temMap.put("toOpenId", openId);
            temMap.put("url", "");
            temMap.put("parm1", wmhMessage.getParm1());
            temMap.put("parm2", wmhMessage.getParm2());
            temMap.put("parm3", wmhMessage.getParm3());
            temMap.put("parm4", wmhMessage.getParm4());
            if (wmhMessage.getTemplateType() == WmhMessageService.TEMPLATE_TYPE_HYTZ) { //会议通知模板
                temMap.put("parm5", wmhMessage.getParm5());
                WeiXinPlatformUtil.sendTemplate(temMap, "5", Constants.platformId); //发送模板消息
            } else if (wmhMessage.getTemplateType() == WmhMessageService.TEMPLATE_TYPE_XZFFTZ) {//薪资发放模板
                WeiXinPlatformUtil.sendTemplate(temMap, "6", Constants.platformId); //发送模板消息
            }else if (wmhMessage.getTemplateType() == WmhMessageService.TEMPLATE_TYPE_ZSXGRZTZ) {//周师修改日志通知模板
                WeiXinPlatformUtil.sendTemplate(temMap, "7", Constants.platformId); //发送模板消息
            }

        }
    }

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMailAccount,String userName,String mailTitle,String  mailContent) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "微门户", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount,userName, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(mailTitle, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(mailContent, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /**
     * @param phone
     * @param realName
     * @return String    返回类型
     * @Title: getUserInfoByPhone
     * @Description: 根据手机号查询用户ID
     * @author wd
     * @Date 2017年3月8日  上午11:54:51
     */
    @Override
    public Map<String, Object> getUserInfoByPhone(String phone, String realName) {
        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> map = wmhUserTagMapper.getUserInfoByPhone(phone);
        if (CollectionUtils.isEmpty(map)) {
            data.put("code", 0);
            data.put("msg", "该用户不存在！");
            return data;
        }

        String real_name = (String) map.get("real_name");
        if (!realName.equals(real_name)) {
            data.put("code", 0);
            data.put("msg", "您输入的手机号和用户名不匹配！");
            return data;
        }
        data.put("code", 1);
        data.put("user_id", map.get("id"));
        return data;
    }

    /**
     * @return Map<String,List<Map<String,Object>>>    返回类型
     * @Title: getOftenAndLastUseTags
     * @Description: 获取常用标签和最新使用的标签
     * @author wd
     * @Date 2017年3月8日  下午1:53:31
     */
    @Override
    public Map<String, List<Map<String, Object>>> getOftenAndLastUseTags() {
        Map<String, List<Map<String, Object>>> data = new HashMap<String, List<Map<String, Object>>>();
        List<Map<String, Object>> oftenUseTags = wmhTagUseMapper.getOftenUseTags();
        List<Map<String, Object>> lastUseTags = wmhTagUseMapper.getLastUseTags();
        data.put("often_use_tags", oftenUseTags);
        data.put("last_use_tags", lastUseTags);
        return data;
    }

    //查询所有的消息推送历史
    @Override
    public List<Object> searchAllMsg(WmhMessage wmhMessage) throws Exception {
        int pageSize = Constants.wmhPageSize;
        int startNum = pageSize * (wmhMessage.getCurrentpage() - 1);
        RowBounds rowBounds = new RowBounds(startNum, pageSize);
        PaginationInterceptor.startPage(wmhMessage.getCurrentpage(), wmhMessage.getRscount());
        List<WmhMessage> wmhMessageList = wmhMessageMapper.searchAllMobileListPage(wmhMessage, rowBounds);
        if (null != wmhMessageList && wmhMessageList.size() > 0) {
            //用户显示这条消息的标签以及标签人数
            for (int i = 0; i < wmhMessageList.size(); i++) {
                WmhMessage wmhMessage1 = wmhMessageList.get(i);
                String tagId = wmhMessage1.getToUser();
                if (!StringUtils.isEmpty(tagId)) {
                    if (tagId.contains(",")) {
                        String[] b = tagId.split(",");
                        List<String> tagList = new ArrayList<>();
                        if (b.length > 0) {
                            for (int j = 0; j < b.length; j++) {
                                List<WmhUserTag> wmhUserTagList = wmhUserTagMapper.searchTagById(b[j]);
                                if (null != wmhUserTagList && wmhUserTagList.size() > 0) {
                                    WmhUserTag wmhUserTag = wmhUserTagList.get(0);
                                    if (null != wmhUserTag) {
                                        String tag = wmhUserTag.getTagName() + " " + wmhUserTagList.size() + "人";
                                        tagList.add(tag);
                                    }

                                }
                            }
                            wmhMessage1.setTagList(tagList);
                        }
                    } else {
                    	//不带, 是用户名  仅一个  依id 查用户姓名
                        List<String> tagList = new ArrayList<>();
                      WmhUser wmhUser=  wmhUserMapper.selectByPrimaryKey(tagId);
                      if (wmhUser!=null) {
                    	  String tag=wmhUser.getRealName()+" 1人";
                    	  tagList.add(tag);
                    	  wmhMessage1.setTagList(tagList);
					}
                        
                     /*   List<WmhUserTag> wmhUserTagList = wmhUserTagMapper.searchTagById(tagId);
                        if (null != wmhUserTagList && wmhUserTagList.size() > 0) {
                            WmhUserTag wmhUserTag = wmhUserTagList.get(0);
                            if (null != wmhUserTag) {
                                String tag = wmhUserTag.getTagName() + " " + wmhUserTagList.size() + "人";
                                tagList.add(tag);
                            }
                            wmhMessage1.setTagList(tagList);
                        }*/
                        
                        
                        
                        
                    }
                }
            }
        }
        BaseModel baseModel = PaginationInterceptor.endPage();
        List<Object> list = new ArrayList<Object>();
        list.add(wmhMessageList);
        list.add(baseModel.getCurrentpage());
        list.add(baseModel.getRscount());
        return list;
    }
    
    @Override
	public void selectTotalPages(Map model){
		Map<String, Object> data  = wmhMessageMapper.selectMessagesCount();
		if(CollectionUtils.isEmpty(data)){
			model.put("totalPage", "0");
		}else{
			long message_count = (long) data.get("message_count");
			long totalPage = 0;
			if(message_count%Constants.wmhPageSize==0){  
				totalPage=message_count/Constants.wmhPageSize;  
	        }else{  
	        	totalPage=message_count/Constants.wmhPageSize+1;  
	        } 
			model.put("totalPage", totalPage);
		}
	}

    @Override
    public WmhMessage searchById(String id) throws Exception {
        WmhMessage wmhMessage=wmhMessageMapper.selectByPrimaryKey(id);
        return wmhMessage;
    }


}
