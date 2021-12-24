package com.tian.config.interceptor;


import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.tian.utils.result.DefinitionException;
import com.tian.utils.token.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ projectName:    Springboot
 * @ package:        com.tian.config
 * @ className:      AuthorizeFilter
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 10:14
 * @ version:    1.0
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor,InnerInterceptor {
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * 在访问Controller某个方法之前这个方法会被调用。
     * @param request
     * @param response
     * @param handler
     * @return false则表示不执行postHandle方法,true 表示执行postHandle方法
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ( !(handler instanceof HandlerMethod)){
            return true;//如果不是映射到方法直接通过
        }
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        //检查有没有需要用户权限的注解
        if(requestURI.contains("/user/login")){
            return true;//放行
        }else if (token == null){
            throw new DefinitionException("无token，请重新登录");
        }else {
            String s = JWTUtils.validateToken(token);
            request.setAttribute("requestId",s);
            request.setAttribute("requestIds",s+1);
            return true;
        }
    }


    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * preHandle方法处理之后这个方法会被调用，如果控制器Controller出现了异常，则不会执行此方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("Token Interceptor postHandle");
    }

    /**
     * 不管有没有异常，这个afterCompletion都会被调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Token Interceptor afterCompletion");
    }
}
