package com.deepblue.inaction_00_spring.chapter_06_mine.example_002_methodreturn_advice;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 第2种增强: 后置增强, 没有设置 切点位置, 对当前 target 中的所有方法有效
 */
public class Main {

    public static void main(String[] args) {

        MineReturn mineReturn = new MineReturn();
        MineReturnAdvice returnAdvice = new MineReturnAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(mineReturn);
        factory.addAdvice(returnAdvice);

        MineReturn proxy = (MineReturn) factory.getProxy();
        proxy.greet2("zhangsan");

        System.out.println("------------------------------------------------------");

        proxy.serve2("lisi");

        System.out.println("------------------------------------------------------");

        // 没有 代理就没有 后置增强 这一说!
        mineReturn.greet2("wangwu");
        mineReturn.serve2("zhaoliu");

    }
}
