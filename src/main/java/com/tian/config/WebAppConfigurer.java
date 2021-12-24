package com.tian.config;

import com.tian.config.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ projectName:    Springboot
 * @ package:        com.tian.config
 * @ className:      WebAppConfigurer
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 10:46
 * @ version:    1.0
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(new RequestInterceptor());//请求的拦截器
    }
}
