package com.deepblue.inaction_102_pattern.pattern_02_proxy_dynamic;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProxyHandler implements InvocationHandler {

    private Subject realSubject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("ProxyHandler invoke method before ...");

        System.out.println("proxy   is :" + JSON.toJSONString(proxy));
        System.out.println("method  is :" + JSON.toJSONString(method));
        System.out.println("args    is :" + JSON.toJSONString(args));
        System.out.println("subject is :" + JSON.toJSONString(realSubject));

        Object invoke = method.invoke(realSubject, args);

        System.out.println("ProxyHandler invoke method after  ...");

        return invoke;
    }

}
