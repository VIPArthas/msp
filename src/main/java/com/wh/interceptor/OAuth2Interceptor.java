package com.wh.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wh.base.PageBounds;
import com.wh.framework.OAuthRequired;
import com.wh.mspentity.MspPlatform;
import com.wh.mspentity.MspUser;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.msp.MspUserService;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import com.wh.util.msp.Constants;
import com.wh.util.msp.QiYeUtil;
import com.wh.util.msp.Result;

public class OAuth2Interceptor implements HandlerInterceptor {

	@Autowired
	private MspPlatformService platformService;
	@Autowired
	private MspUserService mspUserService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("**执行顺序: 3、afterCompletion**");

	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2,
			ModelAndView modelAndView) throws Exception {
		System.out.println("**执行顺序: 2、postHandle**");

	}

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		MspPlatform mspPlatform = platformService.load(Constants.MSPPLATFORMID);

		System.out.println("**执行顺序: 1、preHandle**");
		String url = request.getRequestURL().toString();

		HttpSession session = request.getSession();
		// 先判断是否有注解

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		OAuthRequired annotation = method.getAnnotation(OAuthRequired.class);
		if (annotation != null) {
			System.out.println("OAuthRequired：你的访问需要获取登录信息！");

			Object teacherId = session.getAttribute("teacherId");
			log.info("移动校园平台teacherId:" + teacherId);
			Object objUid = session.getAttribute("UserId");
			log.info("移动校园平台UserId:" + objUid);

			if (objUid != null && teacherId != null) {
				return true;
			}

			if (objUid == null) {
				String code = request.getParameter("code");
				if (StringUtil.isEmpty(code)) {
					log.info("移动校园平台UserId为null,开始获取个人code:");
					QiYeUtil.getCode(request, response);
					return false;
				} else {
					log.info("获取到用户的code:{}", code);
					// 根据code获取用户信息
					Result result = QiYeUtil.oAuth2GetUserByCode(mspPlatform.getAccessToken(), code,
							Constants.MSPAGENTID);
					String userId = (String) result.getObj();
					if (StringUtil.isNotEmpty(userId)) {
						// 有此人userId 查看是否关联金智账号
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("userId", userId);
						List<MspUser> mspUserList = mspUserService.findList(map, new PageBounds());
						if (mspUserList != null) {
							if (mspUserList.size() == 1) {
								MspUser mspUser = mspUserList.get(0);
								teacherId = mspUser.getTeacherId();
								// 以teacherId做学生或老师的id
								if (StringUtil.isNotEmpty(teacherId)) {
									session.setAttribute("UserId", userId);
									session.setAttribute("mspUser", mspUser);
									session.setAttribute("teacherId", teacherId);
									return true;
								} else {
									// 已有userId,但未绑定,转向登陆账号密码页面,调用金智接口判断

								}

							} else {
								log.info("无法获取此人userId,可能不在此应用服务范围权限内");
								return false;

							}
						} else {
						
							log.info("mspUserList为null,userId:" + userId);
							
							//根据userId,accessToken获取
							// 创建userId下用户
							MspUser mspUser = new MspUser();
							mspUser.setId(UUIDUtil.getUUID());
							mspUser.setCreateTime(new Date());
							mspUser.setUserId(userId);

							// 保存此userId下mspUser
							mspUserService.save(mspUser);
							// 已有userId,但未绑定,转向登陆账号密码页面,调用金智接口判断
							

						}

					} else {

						log.info("无法获取此人userId,可能不在此应用服务范围权限内");
						response.sendRedirect("/resource/wx/unsubscribe.html");

					}

				}

				/*
				 * String resultUrl = request.getRequestURL().toString(); String
				 * param=request.getQueryString(); if(param!=null){ resultUrl+=
				 * "?" + param; } System.out.println("resultUrl="+resultUrl);
				 * try { resultUrl = java.net.URLEncoder.encode(resultUrl,
				 * "utf-8"); } catch (UnsupportedEncodingException e) {
				 * e.printStackTrace(); } //请求的路径 String
				 * contextPath=request.getContextPath();
				 * response.sendRedirect(contextPath + "/oauth2.do?resultUrl=" +
				 * resultUrl);
				 */
				return false;
			} else {
				// 根据UserId判断用户下是否已绑定学号/工号,密码 ,若有直接登陆,若无,进入金智系统,注册绑定
				
			}
		}
		return true;
	}

}