package pattern.observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pattern.Application;
import pattern.observer.springdemo.PaymentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class TestObserverPattern {

    @Autowired
    PaymentService paymentService;

    @Before
    public void init() {
    }

    @Test
    public void testSpringDemo() {
        System.out.println("==========================================================================================>");
        paymentService.pay(1, "支付成功");
        System.out.println("==========================================================================================>");
    }

    @Test
    public void testSpringDemoAsyn() {
        System.out.println("==========================================================================================>");
        paymentService.payAsyn(1, "支付成功");
        System.out.println("==========================================================================================>");
    }
}
