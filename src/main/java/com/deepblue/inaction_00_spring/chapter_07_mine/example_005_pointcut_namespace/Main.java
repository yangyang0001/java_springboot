package com.deepblue.inaction_00_spring.chapter_07_mine.example_005_pointcut_namespace;

import com.alibaba.fastjson.JSON;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();
        MineAspect aspect = new MineAspect();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(object);
        factory.addAspect(aspect);

        MineObject proxy = factory.getProxy();
        Book book = proxy.selectId(1000L);
        System.out.println("book json is " + JSON.toJSONString(book));

        System.out.println("------------------------------------------------------------------------------------");

        proxy.deleteId(1000L);

    }
}
