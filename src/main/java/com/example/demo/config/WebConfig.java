package com.example.demo.config;

import com.example.demo.interceptor.LoginInterseptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterseptor loginInterseptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterseptor)//注册拦截器
                .excludePathPatterns("/user/login","/user/register"); //不应该拦截注册和登录接口

    }
}
