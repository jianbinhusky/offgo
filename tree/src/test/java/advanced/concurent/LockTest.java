package advanced.concurent;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hujianbin on 18/1/13.
 */
public class LockTest {

    @Test
    public void test1() {
//        Lock lock = new ReentrantLock();
//        System.out.println(Thread.currentThread().getName() + "\t before get lock main--->");
//        lock.lock();
//        System.out.println(Thread.currentThread().getName() + "\t after get lock main--->");
//        Thread t1 = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "\t before get lock t1--->");
//            try {
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "\t after get lock t1---->");
//        });
//        t1.start();
////        t1.interrupt();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Condition condition = lock.newCondition();
////        try {
////
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        lock.unlock();
//        //            condition.await();
//
//        System.out.println("main end");
//
////        while (true) {
////
////        }
    }

    public static void print(Object object,Object o) throws InterruptedException {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            int count = 0;
            while (true) {
                count++;
                System.out.println(Thread.currentThread().getName() + " = "+count);
                Thread.sleep(1000);
                if (count>5) {
                    break;
                }
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName()+" 等到了O锁执行了");
                    o.notify();
                    break;
                }
            }
            System.out.println("跳出while循环了");
        }
    }

    public static void sing(Object object,Object o) throws InterruptedException {
        synchronized (o) {
            System.out.println("sing");
            int count = 0;
            while (true) {
                count++;
                System.out.println(Thread.currentThread().getName() + " = "+count);
                o.wait();
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName()+ " 等到了object锁");
                    break;
                }
            }
        }

    }

//    @Test
//    public void test2() {
    public static void main(String[] args) throws InterruptedException {

        /**
         * 导致死锁
         */
        Object object = new Object();
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            try {
                print(object,o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                sing(object,o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setName("t2");
        t2.start();


        t1.join();
        System.out.println("t1 dead");
        t2.join();
        System.out.println("t2 dead");
//        while (t1.isAlive() || t2.isAlive()){
//            System.out.println("还有其它线程没死，我不能死");
//        }
        System.out.println("End");
    }
}
