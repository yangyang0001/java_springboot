package com.deepblue.inaction_102_pattern.pattern_02_proxy_dynamic;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 */
public class ProxyHandler implements InvocationHandler {

    private Subject subject;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy     is :" + JSON.toJSONString(proxy));
        System.out.println("method    is :" + JSON.toJSONString(method));
        System.out.println("args      is :" + JSON.toJSONString(args));
        System.out.println("subject   is :" + JSON.toJSONString(subject));
        Object result = method.invoke(subject, args);
        return result;
    }


    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }
}
