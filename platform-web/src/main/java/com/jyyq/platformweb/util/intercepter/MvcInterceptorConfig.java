package com.jyyq.platformweb.util.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

//@Configuration
public class MvcInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AdminInterceptor adminInterceptor;
    //排除不拦截的url
    private static final String[] excludePathPatterns = {"/", "/index.html", "/pc/redirect", "/pc/login", "/pc/loginForm", "/static/**", "/lib/**", "/wangEditor/**", "/img/**", "/css/**", "/js/**"};

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 绑定静态资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/**");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //多个拦截器组成一个拦截器链
        //addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        //excludePathPatterns 用户排除拦截
        registry.addInterceptor(adminInterceptor).addPathPatterns("/**").excludePathPatterns(Arrays.asList(excludePathPatterns));
        super.addInterceptors(registry);
    }


    private static final String VIEW_PREFIX = "/WEB-INF/jsp/";// 视图前缀
    private static final String VIEW_SUFFIX = ".jsp";// 视图后缀
    private static final String VIEW_CONTENT_TYPE = "text/html;charset=UTF-8";//视图的内容类型。

    /**
     * 配置 视图解析器
     * @return
     */
    @Bean
    public ViewResolver viewResolver(){

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setCache(true);
        resolver.setPrefix(VIEW_PREFIX);
        resolver.setSuffix(VIEW_SUFFIX);
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setContentType(VIEW_CONTENT_TYPE);
        return resolver;
    }
}
