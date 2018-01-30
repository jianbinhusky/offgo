package advanced.concurrent;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hujianbin on 18/1/29.
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(1);
        stpe.execute(() -> {
            System.out.println("The same as ThreadPoolExecutor execute " + new Date());
        });

        stpe.schedule(() -> {
            System.out.println("delay 1s second                        " + new Date());
        }, 1, TimeUnit.SECONDS);

        stpe.scheduleAtFixedRate(()->{
            System.out.println("delay                                  " + new Date());
        },1,2,TimeUnit.SECONDS);

//        stpe.shutdown();

    }
}
