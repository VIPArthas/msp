package com.wh.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wh.constants.Constants;
import com.wh.entity.UserInfo;
import com.wh.exception.PrivilegeException;

public class PrivilegeUtil {
	/**
	 * 检查用户是否具有指定的权限
	 * 
	 * @param session
	 *            用户session
	 * @param pName
	 *            权限名称
	 * @return  true：有权限，false:没有权限
	 */
	public static boolean check(HttpSession session, String pName) throws PrivilegeException{
		@SuppressWarnings("unchecked")
		Map<String,String> root = (Map<String,String>) session.getAttribute(Constants.user_resource_menus_button);
		if(root==null || root.size()==0){
			return false;
		}
		UserInfo u = (UserInfo) session.getAttribute(Constants.session_user_info);
		if(u==null){
			throw new PrivilegeException("用户未登陆!");
		}
		if(root.get(pName)==null||"".equals(root.get(pName))){
			return false;
		}
		return true;
	}
	
	
}
