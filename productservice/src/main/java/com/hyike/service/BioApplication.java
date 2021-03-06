package com.hyike.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hujianbin on 18/1/13.
 */
public class BioApplication {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accept socket request");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                String apiClassName = objectInputStream.readUTF();
                System.out.println("apiClassName = " + apiClassName);
                String methodName = objectInputStream.readUTF();
                System.out.println("methodName = " + methodName);
                Class[] paramType = (Class[])objectInputStream.readObject();
                for (Class aClass : paramType) {
                    System.out.println(aClass.getName());
                }
                Object[] args4Method = (Object [])objectInputStream.readObject();

                Class clazz = null;

                if (apiClassName.equals(IProductService.class.getName())) {
                    clazz = ProductService.class;
                }

                //反射，通过Service Class 对象 根据方法名和参数数组匹配 要调用的方法
                Method method = clazz.getMethod(methodName, paramType);
                //通过方法反射调用本地Service的具体方法实现
                Object invoke = method.invoke(clazz.newInstance(), args4Method);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(invoke);
                objectOutputStream.flush();

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
