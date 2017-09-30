package com.wh.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.wh.entity.WmhUser;
import com.wh.util.WebUtil;

/**
 * 微门户后台登陆拦截
 * @author Leo
 */
public class WmhWebInterceptor implements HandlerInterceptor {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String requestPath = WebUtil.getRequestPath(request);
		Boolean flag =requestPath.contains("wmh/userManage/web/goIndex.htm");
		
		Boolean flag1 =requestPath.contains("wmh/userManage/web/goIndex2.htm");
		
		if(flag){
			//获取session里的用户信息
			Object user=request.getSession().getAttribute("admin");
			try {
				if(user==null){
					response.sendRedirect(request.getContextPath()+"/wmh/userManage/web/goLogin.htm");
					return false;
				}else
					return true;
			} catch (Exception e) {
				log.error("登陆权限拦截设置异常日志：", e);
			}
		}else if(flag1){
			//获取session里的用户信息
			Object user=request.getSession().getAttribute("admin");
			try {
				if(user==null){
					response.sendRedirect(request.getContextPath()+"/wmh/userManage/web/goLogin2.htm");
					return false;
				}else
					return true;
			} catch (Exception e) {
				log.error("登陆权限拦截设置异常日志：", e);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
