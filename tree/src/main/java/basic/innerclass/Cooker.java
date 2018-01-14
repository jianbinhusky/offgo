package basic.innerclass;

/**
 * Created by hujianbin on 18/1/12.
 */
public class Cooker implements Speaker {

    private String name = "Hyike";

    private static final String LABEL = "CK";

    public static void eat() {
        System.out.println("eat");
        new PrivateStaticCheese();
        new Cooker.PublicStaticCheer();

        new Speaker() {
            @Override
            public void speak() {

            }
        };
    }

    @Override
    public void speak() {
        System.out.println("speak");

        new PrivateNonStaticEntry();
        new PublicNonStaticItem();
        new ProtectedNonStaticFan();
        new NonStaticWater();

        new PrivateStaticCheese();
        new PublicStaticCheer();
        new ProtectedKara();
        new StaticFruit();



        class Voice{
            private String nice;
            int age;
            public void listen() {

            }
        }
    }

    private class PrivateNonStaticEntry{
        private int count;
        public void show() {
            display();
        }

        private void display() {
            System.out.println(name);
        }
    }

    public class PublicNonStaticItem{

    }

    protected class ProtectedNonStaticFan{

    }

    class NonStaticWater{

    }

    static class StaticFruit{

    }

    public static class PublicStaticCheer{

    }

    private static  class PrivateStaticCheese{

    }

    public static void main(String[] args) {

    }

    protected static class ProtectedKara{

    }

    abstract static class AbstractNike {
    }

    public final class FinalAddias {

    }

    public static final class PublicStaticFinalNewbee {

    }


}
