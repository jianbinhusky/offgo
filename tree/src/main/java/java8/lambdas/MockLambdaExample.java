package java8.lambdas;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MockLambdaExample<E> {
    private E element;

    public MockLambdaExample(E element) {
        this.element = element;
    }

    public <R> R apply(Function<? super E, ? extends R> function) {
        return function.apply(element);
    }

    public boolean match(Predicate<? super E> predicate) {
        return predicate.test(element);
    }

    public void consumer(Consumer<? super E> consumer) {
        consumer.accept(element);
    }
}
