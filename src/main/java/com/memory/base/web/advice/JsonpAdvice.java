package com.memory.base.web.advice;

import com.memory.base.util.NiceUtil;
import com.memory.base.web.RequestUtil;

import net.sf.json.util.JSONUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * support JSONP in controller layer using Spring AOP or @AspectJ
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/10 16:13
 * @see {@code spring-mvc.xml} Spring AOP configuration
 */
//@Aspect
public class JsonpAdvice {

    public static final Logger log = LoggerFactory.getLogger(JsonpAdvice.class);

    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    /**
     * a simple jsonp advice
     *
     * @throws Throwable must catch Exceptions in controller layer
     */
    // @Around("execution(* com.memory.*.controller.*.*(..)) && args(request, ..) && @annotation(responseBody)")
    public Object around(ProceedingJoinPoint joinPoint, HttpServletRequest request, ResponseBody responseBody) throws Throwable {
        // TODO just support RequestMethod.GET

        String callback = RequestUtil.getString(request, getCallback());
        Object value = joinPoint.proceed();

        if (NiceUtil.isEmptyString(callback)) {
            return value;
        }

        StringBuilder sb = new StringBuilder("(").append(callback);
        if (JSONUtils.mayBeJSON(value.toString())) {
            sb.append(value.toString()).append(");");
        }
        return sb.toString();
    }

}
