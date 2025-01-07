package com.deepblue.provider;

import com.alibaba.fastjson.JSON;
import com.deepblue.api.EchoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * TODO 不要修改 原始线上 已经提供服务的 RPC 方法! 添加新的方法就OK了!
 */
@Service(interfaceName = "echoService")
public class EchoServiceImpl implements EchoService {


    @Override
    public String sayHello() {
        InetSocketAddress remoteAddress = RpcContext.getContext().getRemoteAddress();
        String  remoteHost = RpcContext.getContext().getRemoteHost();
        Integer remotePort = RpcContext.getContext().getRemotePort();
        Object[] arguments = RpcContext.getContext().getArguments();
        Object   request   = RpcContext.getContext().getRequest();
        Map<String, Object> stringObjectMap = RpcContext.getContext().get();

        System.out.println("say hello method no param, remoteAddress   is :" + JSON.toJSONString(remoteAddress));
        System.out.println("say hello method no param, remoteHost      is :" + remoteHost);
        System.out.println("say hello method no param, remotePort      is :" + remotePort);
        System.out.println("say hello method no param, arguments       is :" + JSON.toJSONString(arguments));
        System.out.println("say hello method no param, request         is :" + JSON.toJSONString(request));
        System.out.println("say hello method no param, stringObjectMap is :" + JSON.toJSONString(stringObjectMap));

        return "Hello!";
    }

    @Override
    public String sayHello(String username) {
        InetSocketAddress remoteAddress = RpcContext.getContext().getRemoteAddress();
        String  remoteHost = RpcContext.getContext().getRemoteHost();
        Integer remotePort = RpcContext.getContext().getRemotePort();
        Object[] arguments = RpcContext.getContext().getArguments();
        Object   request   = RpcContext.getContext().getRequest();
        Map<String, Object> stringObjectMap = RpcContext.getContext().get();

        System.out.println("say hello method has param, remoteAddress   is :" + JSON.toJSONString(remoteAddress));
        System.out.println("say hello method has param, remoteHost      is :" + remoteHost);
        System.out.println("say hello method has param, remotePort      is :" + remotePort);
        System.out.println("say hello method has param, arguments       is :" + JSON.toJSONString(arguments));
        System.out.println("say hello method has param, request         is :" + JSON.toJSONString(request));
        System.out.println("say hello method has param, stringObjectMap is :" + JSON.toJSONString(stringObjectMap));

        return "Hello " + username;
    }
}
