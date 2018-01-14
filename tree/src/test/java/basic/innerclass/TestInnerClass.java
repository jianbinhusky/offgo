package basic.innerclass;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoop;
import org.junit.Test;

import java.nio.channels.SelectionKey;

/**
 * Created by hujianbin on 18/1/12.
 */
public class TestInnerClass {

    @Test
    public void test1() {
        Cooker cooker = new Cooker();
        cooker.speak();
        Cooker.PublicStaticCheer cp = new Cooker.PublicStaticCheer();//创建public的静态内部类对象实例

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        System.out.println(1 << 0);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);

        System.out.println(SelectionKey.OP_READ);
        System.out.println(SelectionKey.OP_WRITE);
        System.out.println(SelectionKey.OP_CONNECT);
        System.out.println(SelectionKey.OP_ACCEPT);

        System.out.println(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

    }
}
