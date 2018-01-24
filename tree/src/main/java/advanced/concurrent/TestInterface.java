package advanced.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public interface TestInterface extends Runnable,Future,Executor{
}
