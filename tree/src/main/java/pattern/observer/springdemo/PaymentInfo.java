package pattern.observer.springdemo;

import lombok.Getter;
import lombok.Setter;

public class PaymentInfo {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String stauts;

    public PaymentInfo(int id, String stauts) {
        this.id = id;
        this.stauts = stauts;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "id=" + id +
                ", stauts='" + stauts + '\'' +
                '}';
    }
}
