package com.deepblue.inaction_01_springboot2.chapter_03.config;

import com.deepblue.inaction_01_springboot2.chapter_03.interceptor.OrderHandlerInterceptor;
import com.deepblue.inaction_01_springboot2.chapter_03.interceptor.SessionHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

/**
 *
 */
@Configuration
public class CommonWebMvcConfig implements WebMvcConfigurer {

    // 拦截器 order 值越小 优先级越高, 默认 order 值为 0;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/*/**").excludePathPatterns("/login*").order(10);
        registry.addInterceptor(new OrderHandlerInterceptor()).addPathPatterns("/*/**").order(5);
    }

    // 跨域访问配置 例子中: 仅仅允许来自 domain2.com 的跨域访问，并且限定访问路径为 /**  方法是 POST 或者 GET
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("web mvc config addCorsMappings method invoke!");
        registry.addMapping("/**")
                .allowedOrigins("http://domain2.com")
                .allowedMethods("GET", "POST");

    }

    /**
     * 格式化
     *      Spring 默认并没有配置如何将字符串转为日期类型, 为了支持可按照指定格式转为日期类型，需要添加一个 DateFormatter 类
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    // URI 到视图的映射 将 http://localhost:8080 或者 http://localhost:8080/ 映射到 http://localhost:8080/index 上
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("web mvc config addViewControllers method invoke!");
        registry.addViewController("/index");
        registry.addRedirectViewController("", "/index");
    }

    // 其他全局定制接口, 这些方法都是从 WebMvcConfigurer 继承!

}
