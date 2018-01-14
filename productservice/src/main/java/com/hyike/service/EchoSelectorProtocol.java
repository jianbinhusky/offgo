package com.hyike.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class EchoSelectorProtocol implements TCPProtocol {

    private static final Logger log = Logger.getLogger(EchoSelectorProtocol.class.getSimpleName());

    private int bufSize;

    public EchoSelectorProtocol(int bufSize) {
        this.bufSize = bufSize;
    }

    @Override

    public void handleAccept(SelectionKey key) throws IOException {
        log.info("handleAccept");
        SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
        clntChan.configureBlocking(false);
        clntChan.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        log.info("handleRead");
        SocketChannel clnChan = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        int bytesRead = clnChan.read(buf);
        if (bytesRead == -1) {
            clnChan.close();
        } else if (bytesRead > 0) {
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
        String msg = new String(buf.array(),0,bytesRead);
        System.out.println(msg);

        String[] mm = msg.split(",");
        for (String s : mm) {
            System.out.println(s);
        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {
        log.info("handleWrite");
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel clntChan = (SocketChannel) key.channel();
        clntChan.write(buf);
        if (!buf.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }
        buf.compact();
    }
}
