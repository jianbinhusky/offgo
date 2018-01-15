package java8.methodreference;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;

/**
 * Test case showing the use of method references.
 */
public class MethodReferenceExampleTest {

    private Foo myFoo;

    @Before
    public void setUp() throws Exception {
        myFoo = new Foo();
    }

    @Test
    public void methodReferencToFooMethod() throws Exception {
        Callable<String> callable = myFoo::doSomething;

        assertEquals(myFoo.doSomething(), callable.call());
    }
}
