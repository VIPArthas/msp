/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageController.java
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

/*
 * 功  能：简单说明该类的功能
 * 
 * 文件名：WmhMessageController.java
 * 
 * 描  述：
 * 
 * [变更履历]
 * Version   变更日                   部署              作者           		变更内容
 * -----------------------------------------------------------------------------
 * V1.00     2017年3月7日       jh        wd    	create
 * -----------------------------------------------------------------------------
 *
 * Copyright (c) 2017   jh All Rights Reserved.
 *┌─────────────────────────────────────────────────—────┐
 *│ 版权声明                                  jh      │        
 *└──────────────────────────────—————————————————————───┘
 */
package com.wh.controller.wmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wh.entity.WmhMessage;
import com.wh.entity.WmhNews;
import com.wh.entity.WmhUser;
import com.wh.service.wmh.WmhUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.controller.common.BaseController;
import com.wh.framework.MethodLog;
import com.wh.service.wmh.WmhMessageService;
import com.wh.util.WebUtil;

import net.sf.json.JSONObject;

/**
 *  消息推送
 *
 * <p>
 * <a href="WmhMessageController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:">wd</a>
 *
 * @version Revision: 1.0  Date: 2017年3月7日 下午1:56:18 
 *
 */
@Controller
@RequestMapping("/wmh/message")
public class WmhMessageController extends BaseController{
	@Autowired
    private WmhMessageService wmhMessageService;
	@Autowired
	private WmhUserService wmhUserService;
	@RequestMapping("/web/toMessagePush.htm")
	public String toMessagePush(HttpServletRequest request,ModelMap map){
		
		map.put("tags",wmhMessageService.getOftenAndLastUseTags());
		return "/wmh/web/message/message_push";
	}
	
	@RequestMapping("/web/toMessagePush1.htm")
	public String toMessagePush1(HttpServletRequest request,ModelMap map){
		
		map.put("tags",wmhMessageService.getOftenAndLastUseTags());
		return "/wmh/wx/manage/message/message_push";
	}
	
	
	@RequestMapping("/web/getTagIdAndUserCount.htm")
	@MethodLog(logKey="根据标签名称查询标签ID和具有该标签的用户数量",logTag="消息推送",logRemark="根据标签名称查询标签ID和具有该标签的用户数量")
	public void getTagIdAndUserCount(HttpServletResponse response,String tagName){
		JSONObject jso = new JSONObject();
		if(StringUtils.isEmpty(tagName)){
			jso.put("code", 0);
			jso.put("msg", "请输入标签!");
			WebUtil.write(response, jso.toString());
			return;
		}
		
		Map<String,Object> map = wmhMessageService.getTagIdAndUserCount(tagName);
		int code = (int) map.get("code");
		jso.put("code", code);
		long count = (long) map.get("count");
		jso.put("count", count);
		String tag_id = (String) map.get("tag_id");
		jso.put("tag_id", tag_id);
		WebUtil.write(response, jso.toString());
	}
	
	/**
	 * 根据用户名,获取用户id及用户数量
	 * @param response
	 * @param userName	用户名
	 */
	@RequestMapping(value="/web/getUserIdAndUserCount.htm",method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	@MethodLog(logKey="根据用户名查询用户名ID和具有该用户名的用户数量",logTag="消息推送",logRemark="根据用户名查询用户名ID和具有该用户名的用户数量")
	public void getUserIdAndUserCount(HttpServletRequest request,HttpServletResponse response,String userName){
		JSONObject jso = new JSONObject();
		
		//userName与标签必填一个即可    后台两个方法无法一起判断,建议前台判断
		/*if(StringUtils.isEmpty(userName)){
			jso.put("code", 0);
			jso.put("msg", "请输入标签！");
			WebUtil.write(response, jso.toString());
			return;
		}*/
		
		List<WmhUser> userList =wmhUserService.getUserByUserName(userName);
		if (userList.size()<=0) {
			jso.put("code", -1);	//代表标签不存在
		}else{
			jso.put("code", 1);	
			jso.put("count", userList.size());
			jso.put("userList", userList);
		}
		WebUtil.write(response, jso.toString());
	}

	
	@RequestMapping("/web/getUserIdByPhone.htm")
	@MethodLog(logKey="根据手机号查询用户ID",logTag="消息推送",logRemark="根据手机号查询用户ID")
	public void getUserIdByPhone(HttpServletResponse response,String phone,String realName){
		JSONObject jso = new JSONObject();
		if(StringUtils.isEmpty(realName)){
			jso.put("code", 0);
			jso.put("msg", "请输入姓名！");
			WebUtil.write(response, jso.toString());
			return;
		}
		if(StringUtils.isEmpty(phone)){
			jso.put("code", 0);
			jso.put("msg", "请输入手机号！");
			WebUtil.write(response, jso.toString());
			return;
		}
		Map<String,Object> map = wmhMessageService.getUserInfoByPhone(phone,realName);
		int code = (int) map.get("code");
		if(code == 0){
			jso.put("code", 0);
			jso.put("msg", (String)map.get("msg"));
			WebUtil.write(response, jso.toString());
			return;
		}
		jso.put("code", 1);
		jso.put("user_id", (String)map.get("user_id"));
		WebUtil.write(response, jso.toString());
	}
	/**
	 * @Description: ${todo}(推送消息历史展示)
	 * @author lp
	 * @date 2017年3月10日15:20:40
	 * @return ${return_type}    返回类型
	 *
	 */
	@RequestMapping("/web/pushMessageHistory.htm")
	public String pushMessageHistory(HttpServletRequest httpServletRequest,HttpServletResponse response,ModelMap modelMap){
		WmhMessage wmhMessage=new WmhMessage();
		try {
			List<Object> historyList=wmhMessageService.searchAllMsg(wmhMessage);
			modelMap.put("historyList",historyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wmh/web/message/sendHistory";
	}
	
	/**
	 * @Description: ${todo}(推送消息历史展示)
	 * @author lp
	 * @date 2017年3月10日15:20:40
	 * @return ${return_type}    返回类型
	 *
	 */
	@RequestMapping("/web/pushMessageHistory1.htm")
	public String pushMessageHistory1(HttpServletRequest httpServletRequest,HttpServletResponse response,ModelMap modelMap){
		try {
			wmhMessageService.selectTotalPages(modelMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/wmh/wx/manage/message/send_history";
	}

	@RequestMapping("/web/goMessagesListData.htm")
	public void goMessagesListData(HttpServletRequest request,WmhMessage wmhMessage,HttpServletResponse response){
		//查询新闻列表
		try {
			JSONObject jso = new  JSONObject();
			List<Object> list = this.wmhMessageService.searchAllMsg(wmhMessage);
			List<Map<String, Object>> messageList = (List<Map<String, Object>>) list.get(0);
	        
	        jso.put("list", messageList);
	        WebUtil.write(response, jso.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 *@Author:liping
	 *@Description: 推送的消息详情
	 *@Date:2017年3月14日17:46:34
	 */
	@RequestMapping("/web/msgDetail.htm")
	public String msgDetail(HttpServletRequest request,HttpServletResponse response,String id,String tagList,ModelMap modelMap){
		if(StringUtils.isEmpty(id)){
			return "";
		}
		String url="";
		try {
			WmhMessage wmhMessage=wmhMessageService.searchById(id);
			List<String> list=new ArrayList<>();
			if(wmhMessage.getTemplateType()==1){
				if(!StringUtils.isEmpty(tagList)){
					tagList=tagList.replace("["," ");
					tagList=tagList.replace("]"," ");
					tagList=tagList.trim();
					String [] t=tagList.split(",");
					if(null!=t && t.length>0){
						for(int i=0;i<t.length;i++){
							String tag=t[i];
							list.add(tag);
						}
					}
					if(list.size()>0){
						wmhMessage.setTagList(list);
					}
				}
				url= "/wmh/web/message/huiyi_detail";
			}else if(wmhMessage.getTemplateType()==2){
				String userId=wmhMessage.getToUser();
				WmhUser u=wmhUserService.selectUserById(userId);
				if(null!=u){
					if(!StringUtils.isEmpty(u.getRealName())){
						modelMap.put("realName",u.getRealName());
					}
					if(!StringUtils.isEmpty(u.getPhone())){
						modelMap.put("phone",u.getPhone());
					}
				}
				url= "/wmh/web/message/xinzi_detail";
			}
			modelMap.put("msg",wmhMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
