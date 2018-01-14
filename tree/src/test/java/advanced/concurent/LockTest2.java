package advanced.concurent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hujianbin on 18/1/13.
 */
public class LockTest2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        System.out.println(Thread.currentThread().getName() + "\t before get lock main--->");
//        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t after get lock main--->");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t before get lock t1--->");
            try {
//                lock.lockInterruptibly();
                boolean flag = lock.tryLock();
                if (flag) {
                    System.out.println("t1 get lock");
                    lock.unlock();
                } else {
                    System.out.println("t1 not get lock");
                }
            } catch (Exception e) {
                System.out.println("Interrupt");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t after get lock t1---->");
        });
        t1.start();
//        t1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Condition condition = lock.newCondition();
//        try {
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lock.unlock();
        //            condition.await();
        t1.interrupt();
        t1.join();
        lock.lock();
        System.out.println(lock.getHoldCount());
        System.out.println("main end");

//        while (true) {
//
//        }
    }
}
