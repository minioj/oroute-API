package com.example.oroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @Author mini_oj
* @category 拦截过滤
* @Description 为项目配置统一拦截器
* @Date 2020-04-04 16:18
* @Param
**/
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    /**
    * @Author mini_oj
    * @category
    * @Description 拦截过滤
    * @Date 2020-04-04 22:09
    * @Param
    **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/yxj/*").excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/**.css").excludePathPatterns("/**.js")
                .excludePathPatterns("/**.png").excludePathPatterns("/**.jpg")
                .excludePathPatterns("/webjars/**").excludePathPatterns("/**.html");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
