package pattern.proxy.simpledemo.test1;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateClassTest {
    public static void main(String[] args) throws IOException {
        byte[] classFile = ProxyGenerator.generateProxyClass("TestProxy", new Class[]{TestInterface.class});
        File file = new File("/root/hujianbin/git/offgo/tree/src/main/java/pattern/proxy/simpledemo/test1/TestProxy.class");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(classFile);
        fos.flush();
        fos.close();
    }
}
