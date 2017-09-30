package com.wh.filter;

import com.wh.entity.TbPlatform;
import com.wh.entity.TbWchatuser;
import com.wh.service.rgpp.PlatformService;
import com.wh.service.rgpp.WchatuserPlatService;
import com.wh.util.Constants;
import com.wh.util.WeiXinPlatformUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 人职匹配过滤器
 * Created by danding on 2016/3/7.
 */
@WebFilter(filterName = "Filter1_PersonCareer", urlPatterns = {"/systemMajor/index.do","/advice/advicePage"})
public class Filter3_PersonCareer implements Filter {
    @Autowired
    private WchatuserPlatService wchatuserPlatManagerImpl;
    @Autowired
    private PlatformService platformManagerImpl;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void init(FilterConfig filterConfig) throws ServletException {

        ServletContext sc = filterConfig.getServletContext();
        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);

        /*if (cxt != null && cxt.getBean("wchatuserPlatManagerImpl") != null && wchatuserPlatManagerImpl == null)
            wchatuserPlatManagerImpl = (WchatuserPlatService) cxt.getBean("wchatuserPlatManagerImpl");
        if (cxt != null && cxt.getBean("platformManagerImpl") != null && platformManagerImpl == null)
            platformManagerImpl = (PlatformService) cxt.getBean("platformManagerImpl");*/
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String platformId = request.getParameter("platformId");
        TbPlatform tbPlatform =  this.platformManagerImpl.getPlatFromById(platformId);
        if (session.getAttribute("openId") == null) {
            String openId = "";
            String code = request.getParameter("code");
            if (code == null) {
            	log.info("xuyycsname:{}", "code is null");
                String req_uri = req.getRequestURI();
                String param = req.getQueryString();
                if (param != null && !"".equals(param.trim())) {
                    req_uri = req_uri + "?" + param;
                }
                req_uri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + tbPlatform.getAppid() + "&redirect_uri=" + URLEncoder.encode(Constants.BASE_SERVER + req_uri,"utf-8")+ "&response_type=code&scope=snsapi_base#wechat_redirect";
                log.info("xuyycsname_req_uri:{}", req_uri);
                ((HttpServletResponse) response).sendRedirect(req_uri);
            } else {
            	log.info("xuyycsname:{}", "code is not null");
            	log.info("xuyycsname_code:{}", code);
                JSONObject object = WeiXinPlatformUtil.requestOpenIdByCode(code,platformId);
                log.info("JSONObject:{}", object);
                if (object.containsKey("openid")) {
                    log.info("openId:{}", object.getString("openid"));
                    openId = object.getString("openid");
                    session.setAttribute("openId", openId);
                    session.setAttribute("platformId", platformId);
                }
                TbWchatuser wchatuser = this.wchatuserPlatManagerImpl.findWchatUserByOpenId(openId);
                if (wchatuser == null) {
                    updateWchatUserInfo(openId,tbPlatform.getAppaccout());
                    String req_uri= req.getRequestURI();
                    String param= req.getQueryString();
                    if(param!=null && !"".equals(param.trim())){
                        req_uri  = req_uri + "?" + param;
                    }
                    ((HttpServletResponse) response).sendRedirect(req_uri);
                } else if(wchatuser.getUserId()==null){//还没有绑定手机
                    /*RequestDispatcher rd = request.getRequestDispatcher("/user/bindMobilePage?requestUrl="+URLEncoder.encode(req.getRequestURI(),"utf-8"));
                    rd.forward(request, response);*/
                	 chain.doFilter(request, response);

                }else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            String openId = (String) session.getAttribute("openId");
            TbWchatuser wchatuser = this.wchatuserPlatManagerImpl.findWchatUserByOpenId(openId);
            if (wchatuser == null) {
                updateWchatUserInfo(openId,tbPlatform.getAppaccout());
                String req_uri= req.getRequestURI();
                String param= req.getQueryString();
                if(param!=null && !"".equals(param.trim())){
                    req_uri  = req_uri + "?" + param;
                }
                ((HttpServletResponse) response).sendRedirect(req_uri);
            }else if(wchatuser.getUserId()==null){//还没有绑定手机
               /* RequestDispatcher rd = request.getRequestDispatcher("/user/bindMobilePage");
                rd.forward(request,response);*/
            	 chain.doFilter(request, response);
            }else {
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {

    }

    /**
     * 保存从微信获取的用户信息
     * @param openId 用户Id
     */
    private void updateWchatUserInfo(String openId,String toUserName){
        TbWchatuser wchatuser = wchatuserPlatManagerImpl.findWchatUserByOpenId(openId);
        if(wchatuser==null){
            wchatuser = new TbWchatuser();
            wchatuser.setCreatetime(new Date());
            wchatuser.setOpenid(openId);
            wchatuser.setSource("2");
        }
        wchatuser.setStatus(1);
        wchatuserPlatManagerImpl.add(wchatuser, toUserName);
    }
}
