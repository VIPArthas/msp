package com.wh.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wh.pojo.MemberInfo;
import com.wh.util.DateUtil;
import com.wh.util.StringUtil;
import com.wh.util.UUIDUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wh.constants.Constants;
import com.wh.entity.TbPlatform;
import com.wh.entity.TbWchatuser;
import com.wh.entity.User;
import com.wh.service.rgpp.PlatformService;
import com.wh.service.rgpp.WchatuserPlatService;

import com.wh.util.WebUtil;
import com.wh.util.WeiXinPlatformUtil;

import java.util.Date;

/**
 * 
 * 
 * @author 徐优优
 * @date 2016年5月6日
 */
public class WxInterceptor implements HandlerInterceptor {

	@Autowired
	private WchatuserPlatService wchatuserPlatService;
	@Autowired
	private PlatformService platformService;
	
	

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 该方法将在请求处理之前进行调用 SpringMVC 中的Interceptor
	 * 是链式的调用的，在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。 每个Interceptor
	 * 的调用会依据它的声明顺序依次执行，而且最先执行的都是Interceptor 中的preHandle 方法，
	 * 所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去
	 * 该方法的返回值是布尔值Boolean 类型的，当它返回为false 时，表示请求结束，后续的Interceptor 和Controller
	 * 都不会再执行；当返回值为true 时就会继续调用下一个Interceptor 的preHandle 方法，如果已经是最后一个Interceptor
	 * 的时候就会是调用当前请求的Controller 方法。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath = WebUtil.getRequestPath(request);

		// 如果包含个人详情页,直接跳过,因为解绑功能,会去除openId
		/*if (requestPath.contains("/wmh/wmhUser/wx/userInfo.htm")||requestPath.contains("/wmh/wmhUser/wx/sendMsgCode.htm")) {
			return true;
		}*/

		if (requestPath.contains("/wx/") && !requestPath.contains("/wchatService/wx/wchatInterface.htm")) {
			HttpSession session = request.getSession();
			// String platformId = "11";
			String platformId = request.getParameter("platformId");
			if (StringUtils.isEmpty(platformId)) {
				platformId = Constants.platformId;
			}
			log.info("platformId:"+platformId);
			// 查询id为1的公众号配置信息
			TbPlatform tbPlatform = platformService.getPlatFromById(platformId);

			log.info("________________________1_____________________________");

			// session里没有放置openId信息
			if (session.getAttribute("openId") == null) {
				String openId = "";
				String code = request.getParameter("code");

				if (StringUtils.isEmpty(code)) {
					log.info("________________________2_____________________________");
					WeiXinPlatformUtil.reqeustCode(response, request, tbPlatform);
					return false;
				} else {
					log.info("________________________3_____________________________");
					log.info("获取到用户的code:{}", code);
					JSONObject object = WeiXinPlatformUtil.getOpenIdStrByCode(code, tbPlatform);
					openId = object.getString("openid");
					if (!StringUtils.isEmpty(openId)) {
						log.info("________________________4_____________________________");
						TbWchatuser wchatuser = this.wchatuserPlatService.findWchatUserByOpenId(openId);
						if (wchatuser == null
								|| (null != wchatuser && wchatuser.getStatus() != 1 && wchatuser.getStatus() != -1)) { // 数据库中没有用户信息
							log.info("________________________4.3_____________________________");
							if ("snsapi_userinfo".equals(object.getString("scope"))) {
								log.info("________________________4.1_____________________________");
								MemberInfo memberInfo = WeiXinPlatformUtil.getMemberInfoBySNS(openId, platformId,
										object.getString("access_token"));// 去微信服务器请求数据,查询用户有没有
																			// 授权
								if (null != memberInfo && !StringUtils.isEmpty(memberInfo.getOpenid())) { // 这里是用户授权登陆的处理方法
									log.info("________________________4.5_____________________________");
									log.info("微信服务器记录，授权登陆的用户：{}", memberInfo.getNickname());
									TbWchatuser tbWchatuser = new TbWchatuser();
									tbWchatuser.setNickName(memberInfo.getNickname());
									tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
									tbWchatuser.setCreatetime(new Date());
									tbWchatuser.setSource("-1");
									tbWchatuser.setOpenid(memberInfo.getOpenid());
									tbWchatuser.setStatus(memberInfo.getSubscribe());
									tbWchatuser.setPlatformId(platformId);
									tbWchatuser.setId(UUIDUtil.getUUID());
									wchatuserPlatService.saveOrAddBySns(tbWchatuser);
									session.setAttribute("openId", openId);
									session.setAttribute("platformId", platformId);
									return true;
								} else {// 尚未关注公众号
									log.info("________________________4.6_____________________________");
									response.sendRedirect("/resource/wx/unsubscribe.html");
								}
							} else {
								log.info("________________________4.2_____________________________");
								MemberInfo memberInfo = WeiXinPlatformUtil.getMemberInfo(openId, platformId);// 去微信服务器请求数据,查询用户有没有关注
								if (null != memberInfo && !StringUtils.isEmpty(memberInfo.getOpenid())) { // 这里是关注过公众号，但是因为一些原因，没有记录到数据库中的处理方法
									log.info("微信服务器记录关注，数据库没有及时获取的用户：{}", memberInfo.getNickname());
									TbWchatuser tbWchatuser = new TbWchatuser();
									log.info("________________________4.7_____________________________");
									tbWchatuser.setNickName(memberInfo.getNickname());
									tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
									tbWchatuser.setCreatetime(new Date());
									tbWchatuser.setSource("-1");
									tbWchatuser.setOpenid(memberInfo.getOpenid());
									tbWchatuser.setStatus(memberInfo.getSubscribe());
									tbWchatuser.setPlatformId(platformId);
									tbWchatuser.setId(UUIDUtil.getUUID());
									wchatuserPlatService.add(tbWchatuser);
									session.setAttribute("openId", openId);
									session.setAttribute("platformId", platformId);
									return true;
								} else {// 尚未关注公众号,去关注公众号
									log.info("________________________4.8_____________________________");
									response.sendRedirect("/resource/wx/unsubscribe.html");
								}
							}

						} else {
							log.info("________________________4.4_____________________________");
							session.setAttribute("openId", openId);
							session.setAttribute("platformId", platformId);
							return true;
						}
					} else {
						response.sendRedirect("/resource/wx/unsubscribe.html");
					}
				}
			} else {
				log.info("________________________5_____________________________");
				String openId = (String) session.getAttribute("openId");
				TbWchatuser wchatuser = this.wchatuserPlatService.findWchatUserByOpenId(openId);

				// 关注过公众号
				if (null != wchatuser && (wchatuser.getStatus() == 1 || wchatuser.getStatus() == -1)) {
					Object userId=null;
					userId=session.getAttribute("userId");
					if (StringUtil.isNotEmpty(userId)) {
						log.info("________________________5.1_____________________________");
						return true;
					}else{
					
					}

					log.info("________________________6_____________________________");
					return true;
				} else {
					log.info("________________________7_____________________________");
					// status:0未关注过公众号
					MemberInfo memberInfo = WeiXinPlatformUtil.getMemberInfo(openId, platformId);// 去微信服务器请求数据,查询用户有没有关注
					if (null != memberInfo && !StringUtils.isEmpty(memberInfo.getOpenid())) { // 这里是关注过公众号，但是因为一些原因，没有记录到数据库中的处理方法
						log.info("微信服务器记录关注，数据库没有及时获取的用户：{}", memberInfo.getNickname());
						TbWchatuser tbWchatuser = new TbWchatuser();
						log.info("________________________4.7_____________________________");
						tbWchatuser.setNickName(memberInfo.getNickname());
						tbWchatuser.setHeadImg(memberInfo.getHeadimgurl());
						tbWchatuser.setCreatetime(new Date());
						tbWchatuser.setSource("-1");
						tbWchatuser.setOpenid(memberInfo.getOpenid());
						tbWchatuser.setStatus(memberInfo.getSubscribe());
						tbWchatuser.setPlatformId(platformId);
						tbWchatuser.setId(UUIDUtil.getUUID());
						wchatuserPlatService.add(tbWchatuser);
						session.setAttribute("openId", openId);
						session.setAttribute("platformId", platformId);
						return true;
					} else {// 尚未关注公众号,去关注公众号
						log.info("________________________4.8_____________________________");
						response.sendRedirect("/resource/wx/unsubscribe.html");
					}
					//response.sendRedirect("/resource/wx/unsubscribe.html");
				}
			}
		} else {
			return true;
		}
		log.info("________________________8_____________________________");
		return false;
	}

	/**
	 * 由preHandle 方法的解释我们知道这个方法包括后面要说到的afterCompletion 方法都只能是在当前所属的Interceptor
	 * 的preHandle 方法的返回值为true 时才能被调用 postHandle
	 * 方法，顾名思义就是在当前请求进行处理之后，也就是Controller 方法调用之后执行， 但是它会在DispatcherServlet
	 * 进行视图返回渲染之前被调用， 所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO
	}

	/**
	 * 该方法也是需要当前对应的Interceptor 的preHandle 方法的返回值为true 时才会执行。
	 * 顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
	 * 这个方法的主要作用是用于进行资源清理工作的
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO
	}

}
