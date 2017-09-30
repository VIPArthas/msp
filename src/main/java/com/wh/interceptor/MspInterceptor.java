package com.wh.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wh.base.PageBounds;
import com.wh.mspentity.MspPlatform;
import com.wh.mspentity.MspUser;
import com.wh.service.msp.MspPlatformService;
import com.wh.service.msp.MspUserService;
import com.wh.util.StringUtil;
import com.wh.util.WebUtil;
import com.wh.util.msp.Constants;
import com.wh.util.msp.QiYeUtil;
import com.wh.util.msp.Result;

/**
 * 移动校园平台拦截器
 * 
 * @author Administrator Leo
 *
 */
public class MspInterceptor implements HandlerInterceptor {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private MspPlatformService platformService;

	@Resource
	private MspUserService mspUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String requestPath = WebUtil.getRequestPath(request);
		Boolean flag = requestPath.contains("/msp/mspUser/wx/myInfo.htm");
		if (flag) {
			// 获取session里的用户信息
			String wxid = (String) request.getSession().getAttribute("wxid");
			
			String userid = (String) request.getSession().getAttribute("userid");
		
			if (wxid == null) {
				String code = request.getParameter("code");
				if (StringUtil.isNotEmpty(code)) {
					log.info("MSP项目首页用户code:" + code);
					MspPlatform mspPlatform = platformService.load(Constants.MSPPLATFORMID);
					Result result = QiYeUtil.oAuth2GetUserByCode(mspPlatform.getAccessToken(), code,
							Constants.MSPAGENTID);
					wxid = (String) result.getObj();
					// 获取到用户的应用内userid 看是否绑定有用户 有直接继续,没有就跳转
					if (StringUtil.isNotEmpty(wxid)) {
						request.getSession().setAttribute("wxid", wxid);
						getUserid(request, wxid);
						return true;
					} else {
						log.error("MSP项目用户wxid未获取成功!");
						return false;
					}
				} else {
					// 获取code
					log.error("MSP项目首页去获取code:" + code);
					String corpid = Constants.CORPID;
				    String req_uri = request.getRequestURI();
					String redirect_uri=com.wh.util.Constants.BASE_SERVER+req_uri;
					log.error("MSP项目首页去获取--------------------->redirect_uri:"+redirect_uri);
					//String redirect_uri = "http://msp.uni-uni.cn/msp/mspUser/wx/myInfo.htm";
					try {
						redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");
					} catch (UnsupportedEncodingException e) {
						log.error("企业号获取code,重定向地址编码错误:" + e.toString());

					}
					String oauth2Url = Constants.OAUTH2_URL;
					
					log.error("MSP项目首页去获取code:------------------1");
					oauth2Url = oauth2Url.replace("CORPID", corpid).replace("REDIRECT_URL", redirect_uri);
					log.error("MSP项目首页去获取code:------------------2+oauth2Url:"+oauth2Url);
					try {
						response.sendRedirect(oauth2Url);
						log.error("MSP项目首页去获取code:------------------3");
						return false;
					} catch (IOException e) {
						log.error("企业号获取code,重定向失败:" + e.toString());
					}

				}
			} else {
				if (StringUtil.isNotEmpty(userid)) {
					return true;
				} else {
					// 根据wxid获取userid
					getUserid(request,wxid);
					return true;
				}

			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 根据wxid获取userid
	 * @param request
	 * @param wxid	在表中为user_id
	 * @return	userid,在表中为主键id
	 */
	public String getUserid(HttpServletRequest request, String wxid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", wxid);
		String userid = "";
		List<MspUser> muList = mspUserService.findList(map, new PageBounds());
		if (muList != null && muList.size() > 0) {
			userid = muList.get(0).getId();
			request.getSession().setAttribute("userid", userid);
			log.info("根据用户wxid:" + wxid + ",获取的userid:" + userid);
		}
		return userid;
	}

}
