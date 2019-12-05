package com.icbc.patrol.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/12/2 15:08
 * @description 通过WebFilter注解实现过滤器
 * @intro ：@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器
 */
//@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
public class LogCostFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.println("LogFilter2 Execute cost = " + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
