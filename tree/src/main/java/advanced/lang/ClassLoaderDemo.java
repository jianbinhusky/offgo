package advanced.lang;

/**
 * Created by hujianbin on 18/1/20.
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {

        System.out.println("BootStrapClassLoader...");
        String[] bootpath = System.getProperty("sun.boot.class.path").split(":");
        for (String s : bootpath) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("ExtClassLoader...");
        String[] extdir = System.getProperty("java.ext.dirs").split(":");
        for (String s : extdir) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("AppClassLoader...");
        String[] appdir = System.getProperty("java.class.path").split(":");
        for (String s : appdir) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("Current ClassLoaderDemo Class's ClassLoader is : "+ClassLoaderDemo.class.getClassLoader());
        System.out.println("Current ClassLoaderDemo Class's Parent ClassLoader is : " + ClassLoaderDemo.class.getClassLoader().getParent());

        System.out.println();
        System.out.println("String.class ClassLoader is : "+String.class.getClassLoader());
    }
}
