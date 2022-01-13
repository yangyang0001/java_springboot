package com.deepblue.inaction_01_springboot2.chapter_03.interceptor;

import com.alibaba.fastjson.JSON;
import com.deepblue.inaction_01_springboot2.chapter_03.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class SessionHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("session interceptor preHandle  method invoke, user is :" + JSON.toJSONString(user));

        if(user == null) {
            // 没有登录, 重定向到 login.html
            // response.sendRedirect("/login.html");
            // return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Controller 中方法执行完毕后 调用此方法
        System.out.println("session interceptor postHandle method invoke!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 页面渲染完毕后 调用此方法
    }
}
