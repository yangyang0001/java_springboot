package com.deepblue.inaction_102_pattern.pattern_03_proxy_static;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *
 */
public class CommonMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("object is      :" + JSON.toJSONString(object));
        System.out.println("method is      :" + JSON.toJSONString(method));
        System.out.println("args   is      :" + JSON.toJSONString(args));
        System.out.println("methodProxy is :" + methodProxy);
        return methodProxy.invokeSuper(object, args);
    }
}
