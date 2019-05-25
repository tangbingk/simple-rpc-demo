package com.tangbin.remoterpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = new RPCRequest();
        request.setMethodName(method.getName());
        request.setParameters(args);
        RPCNetTransport rpcNetTransport = new RPCNetTransport(host, port);
        return rpcNetTransport.sendRequest(request);
    }
}
