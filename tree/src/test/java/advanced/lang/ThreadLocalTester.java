package advanced.lang;

import org.junit.Before;
import org.junit.Test;

public class ThreadLocalTester {

    private static ThreadLocalHolder holder;

    @Before
    public void setUp() {
        holder = new ThreadLocalHolder();
    }

    @Test
    public void test1() {

    }
}
