package com.memory.base.web.filter;

import com.memory.base.web.RequestUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * save response & request using <code>ThreadLocal</code> in <code>RequestUtil</code>
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 17:41
 */
public class AopFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        RequestUtil.setHttpServletRequest((HttpServletRequest) request);
        RequestUtil.setHttpServletResponse((HttpServletResponse) response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
