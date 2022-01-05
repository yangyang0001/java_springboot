package com.deepblue.inaction_00_spring.chapter_06.example_006_regexp_method_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_06.NaiveWaiter;
import com.deepblue.inaction_00_spring.chapter_06.Waiter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

/**
 * 第2种切面: 正则表达式切面
 */
public class Main {

    public static void main(String[] args) {

        Waiter waiter = new NaiveWaiter();

        GreetingBeforeAdvice advice = new GreetingBeforeAdvice();

        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        // 特别注意, spring 中的正则表达式符号 和 RabbitMQ 中是不一样的!
        String pattern = ".*greet.*";
        advisor.setPattern(pattern);
        advisor.setAdvice(advice);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvisor(advisor);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.greetTo("zhangsan");
        proxy.serveTo("lisi");

    }
}
