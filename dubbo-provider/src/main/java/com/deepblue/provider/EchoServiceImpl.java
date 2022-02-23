package com.deepblue.provider;

import com.alibaba.fastjson.JSON;
import com.deepblue.api.EchoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 *
 */
@Service(interfaceName = "echoService")
public class EchoServiceImpl implements EchoService {
    @Override
    public String sayHello(String username) {
        InetSocketAddress remoteAddress = RpcContext.getContext().getRemoteAddress();
        String  remoteHost = RpcContext.getContext().getRemoteHost();
        Integer remotePort = RpcContext.getContext().getRemotePort();
        Object[] arguments = RpcContext.getContext().getArguments();
        Object   request   = RpcContext.getContext().getRequest();
        Map<String, Object> stringObjectMap = RpcContext.getContext().get();

        System.out.println("remoteAddress   is :" + JSON.toJSONString(remoteAddress));
        System.out.println("remoteHost      is :" + remoteHost);
        System.out.println("remotePort      is :" + remotePort);
        System.out.println("arguments       is :" + JSON.toJSONString(arguments));
        System.out.println("request         is :" + JSON.toJSONString(request));
        System.out.println("stringObjectMap is :" + JSON.toJSONString(stringObjectMap));

        return "Hello " + username;
    }
}
