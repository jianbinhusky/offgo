package pattern.observer.springdemo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MailPaymentStatusUpdateListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == PaymentStatusUpdateEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == PaymentInfo.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("邮邮Email, 收到支付状态更新的通知. " + event+ " - Thread: " + Thread.currentThread().getName());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

