package com.qrs.auoth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zyx 2134208960@qq.com
 * @version 0.1.0
 * @create 2021-03-16 11:18
 * @since 0.1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")// 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
                .excludePathPatterns("/main","/","/js/**","/callback","/css/**","/favicon.ico"
                ,"/swagger-ui.html/**","/swagger/**","/swagger-resources/**"
                ,"/v2/**","/webjars/**","/configuration","/doc.html/**");
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
