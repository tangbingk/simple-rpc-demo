package com.tangbin.remoterpc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        IHelloService iHelloService = new HelloServiceImpl();
        RpcServerProxy rpcServerProxy = new RpcServerProxy();
        rpcServerProxy.publisher(iHelloService, 8000);
    }
}
