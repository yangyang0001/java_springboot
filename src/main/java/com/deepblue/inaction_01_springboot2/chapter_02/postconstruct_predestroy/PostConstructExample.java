package com.deepblue.inaction_01_springboot2.chapter_02.postconstruct_predestroy;

import com.deepblue.inaction_01_springboot2.chapter_02.datasource.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 */
@Component
public class PostConstructExample {

    /**
     * 当 Bean 被容器初始化后，会调用 ＠PostConstruct 的注解方法 ：
     */
    @PostConstruct
    public void init() {
        System.out.println("PostConstructExample init method invoke");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PostConstructExample destroy method invoke");
    }


}
