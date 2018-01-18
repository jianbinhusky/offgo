package advanced.concurrent;

import java.util.concurrent.*;

public class ExecutorsCase {

    public static void main(String[] args) {
//        ExecutorService es = Executors.newFixedThreadPool(1);
        RejectedExecutionHandler[] handler = {new ThreadPoolExecutor.AbortPolicy(),
                new ThreadPoolExecutor.CallerRunsPolicy(),
                new ThreadPoolExecutor.DiscardOldestPolicy(), new ThreadPoolExecutor.DiscardPolicy()};
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(1, 2, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1), Executors.defaultThreadFactory(), handler[0]);
        String[] names = {"Concurrent","NIO","Pattern","Base"};
//        ExecutorService tpe = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
//            tpe.execute(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName());
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }bb
//            });
            Mythread mythread = new Mythread(i,names[i%4]);
            try {
                tpe.execute(mythread);
            } catch (RejectedExecutionException re) {
//                re.printStackTrace();
            }
        }
        tpe.shutdown();
        Mythread mythread = new Mythread(11,names[11%4]);
        tpe.execute(mythread);
        System.out.println(">>>EOF " +Thread.currentThread().getName());
    }
}

class Mythread implements Runnable{

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String taskName;

    public Mythread(int id ,String taskName) {
        super();
        this.id = id;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("Task-"+this.getId() +"-" + Thread.currentThread().getName() + "-"  + this.getTaskName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RejectedExecutionException re) {
            re.printStackTrace();
        }
    }
}
