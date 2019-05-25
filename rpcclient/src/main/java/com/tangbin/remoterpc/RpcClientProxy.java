package com.tangbin.remoterpc;

import java.lang.reflect.Proxy;

public class RpcClientProxy {


    /*
     * dynamic proxy:
     * 1. java 原生代理 （基于接口代理）
     * 2. cglib （类或接口代理）
     * 3. javassit
     * 4. asm
     */
    public <T> T clientProxy(Class<T> interfaceCls, String host, int port) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class<?>[] {interfaceCls}, new RemoteInvocationHandler(host, port));
    }
}
