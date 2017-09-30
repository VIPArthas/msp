package com.wh.controller.xlwapp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wh.controller.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wh.entity.UserInfo;
import com.wh.framework.MethodLog;
import com.wh.service.xlwapp.UserService;

/**
* @author: 何阳
* @date: 2016年7月25日
*/
@Controller
@RequestMapping("/admin/User")
public class UserSourceController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/web/selectUserPageList.htm")
	@MethodLog(logKey="用户信息",logTag="每个用户信息",logRemark="用户信息分页列表")
	public String selectUserPageList(ModelMap map,HttpServletRequest request,HttpServletResponse response,UserInfo userInfo){
		List<Object> userList = new ArrayList<Object>();
		try {
			
			userList = userService.selectUserListPage(userInfo);
			map.put("userList", userList);
			map.put("userInfo", userInfo);
			
		} catch (Exception e) {
			log.error("异常日志：", e);
		}
		return "xlwapp/manager/UserInfoList";
	}
	
	
}
