package com.tangbin.remoterpc;

public class HelloServiceImpl implements IHelloService {

    public String sayHello(String content) {
        return "Hello world: " + content;
    }

    public String saveUser(User user) {
        System.out.println("user->" + user);
        return "success";
    }
}
