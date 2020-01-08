package com.group9.musicweb.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/user/index")
                .excludePathPatterns("/user/register");

    }
}
