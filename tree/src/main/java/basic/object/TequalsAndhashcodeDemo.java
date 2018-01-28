package basic.object;

/**
 * Created by hujianbin on 18/1/25.
 */
public class TequalsAndhashcodeDemo {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        System.out.println(object1.hashCode());
        System.out.println(Integer.toHexString(object1.hashCode()));
        System.out.println(Integer.toBinaryString(object1.hashCode()));
        System.out.println(object1.toString());
        System.out.println(object2.hashCode());
        System.out.println(object3.hashCode());

        String s = "test";
    }
}
