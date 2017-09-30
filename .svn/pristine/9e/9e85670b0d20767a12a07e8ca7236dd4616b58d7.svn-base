package com.wh.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.wh.entity.TbLog;
import com.wh.service.rgpp.LogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户操作日志过滤器
 * Created by danding on 2016/3/10.
 */
//@WebFilter(filterName = "Filter3_ApiLog",urlPatterns = {"/user/*","/resume/*","/station/*","/zycp/*","/meet/*"})
public class Filter4_ApiLog implements Filter {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogService logManagerImpl;
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext sc = filterConfig.getServletContext();
        /*XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean("logManagerImpl") != null && logManagerImpl == null){
            logManagerImpl = (LogService) cxt.getBean("logManagerImpl");
        }*/
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        String openId = (String) session.getAttribute("openId");
        if(openId==null){
            openId = "unknow";
        }
        /*Map<String,Object> log = new HashMap<String, Object>();
        log.put("user_id",openId);
        log.put("doType",getTypeByUrl(req.getRequestURI()));
        log.put("doContent", req.getRequestURI());
        log.put("project",1);
        this.logManagerImpl.add(log);*/
        TbLog log = new TbLog();
        log.setUserId(openId);
        log.setDotype(getTypeByUrl(req.getRequestURI()));
        log.setDocontent(req.getRequestURI());
        log.setProject(1);
        this.logManagerImpl.insertSelectiveLog(log);
        chain.doFilter(request, response);
    }

    public void destroy() {

    }

    /**
     * 根据Url地址获得操作类型
     * @return
     */
    private String getTypeByUrl(String url){
        String result = "未知操作";
        //用户更新信息
        if(url.contains("updateUserInfo")){
            result = "更新用户信息";
        }
        //查看用户资料
        else if(url.contains("selectCompany")){
            result = "查看用户资料";
        }
        //更新企业信息
        else if(url.contains("saveCompany")){
            result = "更新企业信息";
        }
        //无权限访问
        else if(url.contains("invalidRolePage")){
            result = "无权限访问";
        }//添加简历
        else if(url.contains("resume/save")){
            result = "添加简历";
        }//查看自己简历
        else if(url.contains("resume/resumeDetail")){
            result = "查看自己简历";
        }//编辑自己简历
        else if(url.contains("editResume")){
            result = "编辑自己简历";
        }//扫一扫
        else if(url.contains("scanMatchResume")){
            result = "扫一扫";
        }//查看匹配结果
        else if(url.contains("lookMatchResume")){
            result = "查看匹配结果";
        }//查看收到的简历
        else if(url.contains("resumeList.do")){
            result = "查看收到的简历";
        }//企业查看简历
        else if(url.contains("lookResumeDetail")){
            result = "企业查看简历";
        }//企业查看推荐简历
        else if(url.contains("recommendResume")){
            result = "企业查看推荐简历";
        }//添加岗位
        else if(url.contains("addStation")){
            result = "添加岗位";
        }//编辑岗位
        else if(url.contains("editStation")){
            result = "编辑岗位";
        }//查看已发布的岗位
        else if(url.contains("publishStations.do")){
            result = "查看已发布的岗位";
        }//投递简历
        else if(url.contains("mailRsume")){
            result = "投递简历";
        }//设置岗位标准
        else if(url.contains("stationStandardSave")){
            result = "设置岗位标准";
        }//推荐岗位
        else if(url.contains("recommendStation")){
            result = "推荐岗位";
        }//发送面试邀请
        else if(url.contains("meetInvitationSend")){
            result = "发送面试邀请";
        }//查询面试邀请
        else if(url.contains("meetList")){
            result = "查询面试邀请";
        }//准备测评
        else if(url.contains("paperPage")){
            result = "准备测评";
        }//保存测评结果
        else if(url.contains("savePaper")){
            result = "保存测评结果";
        }//显示测评结果
        else if(url.contains("testResult")){
            result = "显示测评结果";
        }
        return result;
    }
}
