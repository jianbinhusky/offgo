package pattern.observer.springdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StockPaymentStatusUpdateListener implements ApplicationListener<PaymentStatusUpdateEvent> {
    @Override
    public void onApplicationEvent(PaymentStatusUpdateEvent event) {
        System.out.println("Stock, 收到支付状态更新的事件. " + event + " - Thread: " + Thread.currentThread().getName());
    }
}
