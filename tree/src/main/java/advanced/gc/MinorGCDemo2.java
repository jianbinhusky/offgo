package advanced.gc;

public class MinorGCDemo2 {
    public static void main(String[] args) throws InterruptedException {
//        MemoryObject object = new MemoryObject(1024 * 1024);
//        MemoryObject object1 = new MemoryObject(1024 * 1024);
        MinorGCDemo.happenMinorGC(12);
        Thread.sleep(2000);
    }
}
