package com.deepblue.inaction_00_spring.chapter_06_mine.example_006_regexpmethod_pointcut_advisor;

import com.deepblue.inaction_00_spring.chapter_07.example_008_aspectj_joinpoint_info.Mine;
import com.deepblue.inaction_102_pattern.pattern_04_template.service.prop.common.PropCommonService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        MineObject object = new MineObject();
        MineObject2 object2 = new MineObject2();


        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        MineMethodInterceptor interceptor = new MineMethodInterceptor();

        String pattern = ".*greet.*";
        advisor.setPattern(pattern);
        advisor.setAdvice(interceptor);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(object);
        factory.addAdvisor(advisor);

        MineObject proxy1 = (MineObject) factory.getProxy();
        proxy1.greet2("zhangsan");

        System.out.println("--------------------------------------------------------------------------------------------");

        proxy1.serve2("lisi");

        object.greet2("wangwu");
        object.serve2("zhaoliu");

    }

}
