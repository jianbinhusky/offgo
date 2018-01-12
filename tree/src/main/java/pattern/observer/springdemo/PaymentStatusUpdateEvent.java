package pattern.observer.springdemo;

import org.springframework.context.ApplicationEvent;

public class PaymentStatusUpdateEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PaymentStatusUpdateEvent(PaymentInfo source) {
        super(source);
    }
}
