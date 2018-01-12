package pattern.observer.springdemo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentStatusUpdateListener {

    @EventListener(classes = PaymentStatusUpdateEvent.class)
    @Async
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("Order, 收到支付状态更新的通知. " + event+ " - Thread: " + Thread.currentThread().getName());
    }
}
