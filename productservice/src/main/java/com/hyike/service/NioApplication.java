package com.hyike.service;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by hujianbin on 18/1/13.
 */
public class NioApplication {
    private static final int BUFSIZE = 256;

    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        try {
            String[] ports = {"8123","8124","8125"};
            Selector selector = Selector.open();
            for (String arg : ports) {
                ServerSocketChannel listnChannel = ServerSocketChannel.open();
                listnChannel.socket().bind(new InetSocketAddress(Integer.parseInt(arg)));
                listnChannel.configureBlocking(false);
                listnChannel.register(selector, SelectionKey.OP_ACCEPT);
            }

            TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);

            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    System.out.println("选择可操作channel超时");
                    continue;
                }
                Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next();
                    if (key.isAcceptable()) {
                        protocol.handleAccept(key);
                    }

                    if (key.isReadable()) {
                        protocol.handleRead(key);
                    }

                    if (key.isValid() && key.isWritable()) {
                        protocol.handleWrite(key);
                    }
                    keyIter.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
