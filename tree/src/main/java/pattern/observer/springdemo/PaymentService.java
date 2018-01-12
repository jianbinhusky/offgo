package pattern.observer.springdemo;

public interface PaymentService {
    void pay(int status, String msg);

    void payAsyn(int status, String msg);
}
