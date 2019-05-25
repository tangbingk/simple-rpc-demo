package com.tangbin.remoterpc;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {

    Socket socket;
    Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        System.out.println("开始处理客户端请求");
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RPCRequest rpcRequest = (RPCRequest) inputStream.readObject(); //反序列化
            Object result = invoke(rpcRequest);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();

            System.out.println(rpcRequest);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally { // close io stream
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object invoke(RPCRequest request) {
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];

        for (int i = 0; i < args.length; ++i) {
            types[i] = args[i].getClass();
        }

        try {
            Method method = service.getClass().getMethod(request.getMethodName(), types);
            Object result = method.invoke(service, args);
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
