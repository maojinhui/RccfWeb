package com.rccf.model.beans;

import org.aspectj.apache.bcel.verifier.exc.Utility;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class SqlInjectInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {

            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {

                value = clearXss(value);

            }
        }
        return true;
    }


    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }


    /**
     * 处理字符转义 * * @param value * @return
     */
    private String clearXss(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "<").replaceAll(">", ">");
        value = value.replaceAll("\\(", "(").replace("\\)", ")");
        value = value.replaceAll("'", "'");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replace("script", "");
        return value;
    }


}
