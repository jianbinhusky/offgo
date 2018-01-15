package pattern.proxy.simpledemo.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object source;

    public DynamicProxy(Object source) {
        super();
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("接口的方法全部变成这样了 这");
//        return method.invoke(source, args);
        System.out.println("before");
        Method sourceMethod = source.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
//        sourceMethod.setAccessible(true);
        Object result = sourceMethod.invoke(source, args);

        return result;
    }

    public static void main(String[] args) {
        Object object =  Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{TestInterface.class},new DynamicProxy(new TestClass()));
//        object.method1();
//        object.method2();
//        object.method3();
    }
}
