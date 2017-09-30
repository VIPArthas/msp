package com.wh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wh.constants.Constants;
import com.wh.entity.ManageUser;
import com.wh.entity.UserInfo;

/**
 * 未登录用户跳转到登陆页面
 * 
 * @author Administrator
 *
 */
public class LoginUserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		ManageUser loginUser = (ManageUser) req.getSession().getAttribute(Constants.manage_session_user_info);
		if (session == null || loginUser == null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/admin/loginFailure.htm");
			dispatcher.forward(req, resp);
			return;
		}
		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
