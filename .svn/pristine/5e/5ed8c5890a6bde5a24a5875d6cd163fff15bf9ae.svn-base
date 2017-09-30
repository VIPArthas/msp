package com.wh.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 编码统一转换为UTF-8格式
 * Created by danding on 2015/8/17.
 */
@WebFilter(filterName = "Filter0_Encoding",urlPatterns = {"/*"})
public class Filter0_Encoding implements Filter {
    private final static String characterEncoding = "UTF-8";
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(characterEncoding);
        servletResponse.setCharacterEncoding(characterEncoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
    }
}
