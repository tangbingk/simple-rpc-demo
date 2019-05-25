package com.tangbin.remoterpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCNetTransport {

    String host;
    int port;

    public RPCNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        Socket socket = null;

        System.out.println("创建一个新的socket连接");
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("建立连接失败");
            e.printStackTrace();
        }

        return socket;
    }

    public Object sendRequest(RPCRequest request) {
        Socket socket = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {
            socket = newSocket();

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            return result;

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
