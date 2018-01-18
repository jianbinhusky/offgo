package advanced.concurent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeThread75NumbersTester {

    static volatile int limit = 1;
    static final int end = 75;



    @Test
    public void test1() throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(new Mythread(obj,0));
        Thread t2 = new Thread(new Mythread(obj,1));
        Thread t3 = new Thread(new Mythread(obj,2));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(limit);
    }

    public static void print(int id,Object object) throws InterruptedException {
        synchronized (object) {
            while (limit <= end) {
                Thread.sleep(new Random().nextInt(300));
                if (limit / 5 % 3 == id) {
                    System.out.print(id + " : ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print(limit++ + ",");
                    }
                    System.out.println();
                    object.notifyAll();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * wrong example
     */
    @Test
    public void test2() {
        Lock lock=new ReentrantLock(true);
        for(int i=1;i<=3;i++){
            new Thread(new Runner(i,lock)).start();
        }
    }

    static class ReentrantLockRunner implements Runnable {
        public Lock getLock() {
            return lock;
        }

        public void setLock(Lock lock) {
            this.lock = lock;
        }

        private Lock lock;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        public ReentrantLockRunner(int id, Lock lock) {
            this.id = id;
            this.lock = lock;
            this.condition = lock.newCondition();
        }

        private Condition condition;

        @Override
        public void run() {
            boolean flag = lock.tryLock();
            while (limit <= end) {
                if (limit / 5 % 3 == id && flag) {
                    System.out.print(id + " : ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print(limit++ + ",");
                    }
                    System.out.println();
                    lock.unlock();
                } else {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }
        }
    }

    /**
     * wrong example
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread[] ts = new Thread[3];
        for (int i = 0; i < 3; i++) {
            ts[i] = new Thread(new ReentrantLockRunner(i, lock));
            ts[i].start();
        }
        for (Thread t : ts) {
            t.join();
        }
    }

    /**
     * maybe wrong example
     * @param args
     */
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new CyclicBarrierPrinter(i,barrier)).start();
        }
    }

    static class Runner implements Runnable {

        private final int order;
        private final Lock lock;

        public Runner(int order, Lock lock) {
            this.order = order;
            this.lock = lock;
        }

        @Override
        public void run() {
            int start = order * 5 - 5;
            while (start < 75) {
                lock.lock();
                try {
                    for (int i = 0; i < 5; i++) {
                        start++;
                        System.out.print(start + "\t");

                    }
                } finally {
                    lock.unlock();
                }
                start += 10;
                System.out.println();
            }
        }
    }
}


class Mythread implements Runnable {
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Object object;
    private int id;

    public Mythread(Object object, int id) {
        this.id = id;
        this.object = object;
    }
    @Override
    public void run() {
        try {
            ThreeThread75NumbersTester.print(this.getId(),this.getObject());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Number {
    static int c;

    protected void print1(int id) {
        System.out.format("Thread %d: ", id);
        for (int i = 0; i < 5; i++) {
            c++;
            if (i == 4)
                System.out.format("%d%n", c);
            else
                System.out.format("%d, ", c);
        }
    }
}

class CyclicBarrierPrinter extends Number implements Runnable {
    private int id;
    private CyclicBarrier barrier;

    @Override
    public void run() {
        int r = 0;
        long s = System.currentTimeMillis();
        while (c < 75) {
            r++;
            if (c / 5 % 3 == id && c < 75) {
                print1(id);
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        System.out.format("Thread %d: loops %d, execution duration :%d %n", Thread.currentThread().getId(), r,
                System.currentTimeMillis() - s);
    }

    public CyclicBarrierPrinter(int id, CyclicBarrier barrier) {
        super();
        this.id = id;
        this.barrier = barrier;
    }

}


