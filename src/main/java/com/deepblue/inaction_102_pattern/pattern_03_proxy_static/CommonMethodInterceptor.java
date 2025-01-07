package com.deepblue.inaction_102_pattern.pattern_03_proxy_static;

import com.alibaba.fastjson.JSON;
import lombok.*;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommonMethodInterceptor implements MethodInterceptor {

    private Subject realSubject;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("Method Interceptor invoke before ...");

        System.out.println("o       is :" + JSON.toJSONString(o));
        System.out.println("method  is :" + JSON.toJSONString(method));
        System.out.println("objects is :" + JSON.toJSONString(objects));
        System.out.println("subject is :" + JSON.toJSONString(realSubject));

        Object invoke = method.invoke(realSubject, objects);

        System.out.println("Method Interceptor invoke after  ...");

        return invoke;
    }
}
