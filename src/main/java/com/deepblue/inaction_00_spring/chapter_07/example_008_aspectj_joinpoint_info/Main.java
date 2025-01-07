package com.deepblue.inaction_00_spring.chapter_07.example_008_aspectj_joinpoint_info;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        Seller seller = new MineSeller();

        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(seller);
        factory.addAspect(MineAspect.class);

        Seller proxy = factory.getProxy();
        Book book = proxy.getBook(10000L);

        System.out.println("Main method invoke book is : " + JSON.toJSONString(book));

        System.out.println("\n******************************************************************************************************\n");

        String bookName = proxy.getBookName(10000L);

        System.out.println("Main method invoke bookName is : " + bookName);

    }
}
