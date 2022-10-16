package com.yergbro.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")//设置请求方法
                .allowedHeaders("*")//设置响应头
                .allowedOrigins("http://47.118.78.168","http://yergbro.com","http://localhost:7777","http://localhost:8080")//设置访问源，只要在这里加上前端的url即可
                .allowCredentials(true);//设置允许跨域
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //选择要过滤和排除的请求
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**");
////                .excludePathPatterns("/admin/login/status");
////                .excludePathPatterns("/asserts/**");
//    }
}
