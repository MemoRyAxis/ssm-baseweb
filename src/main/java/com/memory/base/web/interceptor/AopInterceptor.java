package com.memory.base.web.interceptor;

import com.memory.base.web.RequestUtil;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * save response & request using {@code ThreadLocal} in {@code RequestUtil}
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 17:41
 */
public class AopInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestUtil.setHttpServletRequest(request);
        RequestUtil.setHttpServletResponse(response);
        return super.preHandle(request, response, handler);
    }
}
