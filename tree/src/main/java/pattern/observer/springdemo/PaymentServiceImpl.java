package pattern.observer.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ApplicationContext context;

//    @Autowired
//    private MailPaymentStatusUpdateListener mailPaymentStatusUpdateListener;
//
//    @Autowired
//    private SmsPaymentStatusUpdateListener smsPaymentStatusUpdateListener;
//
//    @Autowired
//    private StockPaymentStatusUpdateListener stockPaymentStatusUpdateListener;
//
//    @Autowired
//    private OrderPaymentStatusUpdateListener orderPaymentStatusUpdateListener;

    @Autowired
    private SimpleApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void pay(int status, String msg) {
        PaymentInfo paymentInfo = new PaymentInfo(status, msg);
        PaymentStatusUpdateEvent event = new PaymentStatusUpdateEvent(paymentInfo);
        context.publishEvent(event);
    }

    public void payAsyn(int status, String msg) {
        PaymentInfo paymentInfo = new PaymentInfo(status, msg);
        PaymentStatusUpdateEvent event = new PaymentStatusUpdateEvent(paymentInfo);

        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(() -> mailPaymentStatusUpdateListener.onApplicationEvent(event));
//        executorService.execute(() -> smsPaymentStatusUpdateListener.onApplicationEvent(event));
//        executorService.execute(() -> stockPaymentStatusUpdateListener.onApplicationEvent(event));
//        executorService.execute(() -> orderPaymentStatusUpdateListener.onApplicationEvent(event));

        applicationEventMulticaster.setTaskExecutor(executorService);
        applicationEventMulticaster.multicastEvent(event);
    }
}
