package com.wh.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wh.constants.Constants;
import com.wh.entity.UserInfo;



/**
 * @Title:			HandlerInterceptor.java
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

	/**
	 * 
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}

	/**
	 * 后处理回调方法
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("postHandle");
	}

	/**
	 * 整个请求处理完毕回调方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURL().toString());
		HttpSession session = request.getSession(true);
		UserInfo admin = (UserInfo) session.getAttribute(Constants.session_user_info);  
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
        if (admin == null || "".equals(admin.toString())) {  
    		response.sendRedirect(request.getContextPath()+"/admin/admin/login.htm");
        }
		return true;
	}

}
