package com.hyike.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by hujianbin on 18/1/13.
 */
public class RPCUtils {

    public static Object rpc(final Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy, method, args) -> {
            Socket socket = new Socket("127.0.0.1", 8888);

            String apiClassName = clazz.getName();
            String methodName = method.getName();
            Class[] paramType = method.getParameterTypes();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(apiClassName);//Service 的全限定名
            objectOutputStream.writeUTF(methodName);  //调用的方法名
            objectOutputStream.writeObject(paramType);//方法的参数类型
            objectOutputStream.writeObject(args);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

            return object;
        });
    }

    public static Object rpcNIO(final Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (proxy,method,args) ->{
            String server = "127.0.0.1";
            int servPort = 8123;
            byte[] argument = "How Can I earn more money?".getBytes();

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            if (!socketChannel.connect(new InetSocketAddress((server), servPort))) {
                while (!socketChannel.finishConnect()) {
                    System.out.println(".");
                    System.out.println("do something else");
                }
            }

            String apiClassName = clazz.getName();
            String methodName = method.getName();
            Class[] paramType = method.getParameterTypes();

            ByteBuffer writeBuf = ByteBuffer.wrap(apiClassName.getBytes());
            ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
            int totalBytesRcvd = 0;
            int bytesRcvd;
            while (totalBytesRcvd < argument.length) {
                if (writeBuf.hasRemaining()) {
                    socketChannel.write(writeBuf);
                }
                if ((bytesRcvd = socketChannel.read(readBuf)) == -1) {
                    throw new SocketException("Connection closed prematurely");
                }
                totalBytesRcvd += bytesRcvd;
                System.out.println("do other things.");
            }

            System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));
            socketChannel.close();
            Object object = new Object();
            return object;
        });
    }
}
