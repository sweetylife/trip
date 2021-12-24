package com.tian.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ projectName: Springboot
 * @ package: com.tian.config
 * @ className: MPConfig
 * @ author: tian
 * @ description:
 * @ date: 2021/12/21 15:41
 * @ version: 1.0
 */
@Configuration
public class MPConfig {
    @Bean  //各种拦截器
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());//分页拦截器
        return interceptor;
    }
}
