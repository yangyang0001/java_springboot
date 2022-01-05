package com.deepblue.inaction_00_spring.chapter_06.example_005_static_method_matcher_pointcut_advisor;


import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 第1种切面: 静态普通方法名 匹配 切面 测试
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();
        Seller seller = new Seller();

        MethodBeforeAdvice advice = new GreetingBeforeAdvice();

        // 设置切面的 增强, 增强变为其中的属性了
        GreetingAdvisor advisor = new GreetingAdvisor();
        advisor.setAdvice(advice);

        ProxyFactory factory1 = new ProxyFactory();
        factory1.setTarget(waiter);
        factory1.addAdvisor(advisor);
        Waiter proxy1 = (Waiter) factory1.getProxy();
        proxy1.greetTo("zhangsan");
        proxy1.serveTo("lisi");

        System.out.println("-----------------------------------------------------------------------");

        ProxyFactory factory2 = new ProxyFactory();
        factory2.setTarget(seller);
        factory2.addAdvisor(advisor);
        Seller proxy2 = (Seller) factory2.getProxy();
        proxy2.greetTo("zhangsan");
        proxy2.serveTo("lisi");

    }
}
