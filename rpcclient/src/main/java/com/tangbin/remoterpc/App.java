package com.tangbin.remoterpc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        RpcClientProxy proxy = new RpcClientProxy();
        IHelloService iHelloService = proxy.clientProxy(IHelloService.class, "localhost", 8000);
        System.out.println(iHelloService.sayHello("Nero"));

        User user = new User();
        user.setName("Nero");
        System.out.println(iHelloService.saveUser(user));
    }
}
