package com.icbc.patrol.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/12/2 15:08
 */
public class LogCostFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.println("Execute cost = " + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
