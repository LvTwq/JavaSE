package com.example.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 吕茂陈
 * @description
 * @date 2023/3/30 11:31
 */
public class NIO {

    static boolean stop = false;

    public static void main(String[] args) throws IOException {
        int connectionNum = 0;
        int port = 8888;
        ExecutorService service = Executors.newCachedThreadPool();

        // 创建了一个服务端 ssc，并开启一个新的事件选择器，监听它的 OP_ACCEPT 事件
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", port));

        Selector selector = Selector.open();
        /*
         共有 4 种事件类型，分别是：

         新连接事件（OP_ACCEPT）；
         连接就绪事件（OP_CONNECT）；
         读就绪事件（OP_READ）；
         写就绪事件（OP_WRITE）。
         任何网络和文件操作，都可以抽象成这四个事件
         */
        ssc.register(selector, ssc.validOps());

        while (!stop) {
            if (10 == connectionNum) {
                stop = true;
            }
            // 使用 select 函数，阻塞在主线程里。所谓阻塞，就是操作系统不再分配 CPU 时间片到当前线程中，所以 select 函数是几乎不占用任何系统资源的
            int num = selector.select();
            if (num == 0) {
                continue;
            }
            /*
             一旦有新的事件到达，比如有新的连接到来，主线程就能够被调度到，程序就能够向下执行。
             这时候，就能够根据订阅的事件通知，持续获取订阅的事件。
             由于注册到 selector 的连接和事件可能会有多个，所以这些事件也会有多个。
             我们使用安全的迭代器循环进行处理，在处理完毕之后，将它删除
             */
            Iterator<SelectionKey> events = selector.selectedKeys().iterator();
            while (events.hasNext()) {
                SelectionKey event = events.next();

                if (event.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    // 对于我们的数据读取来说，对应的事件就是 OP_READ
                    sc.register(selector, SelectionKey.OP_READ);
                } else if (event.isReadable()) {
                    try (SocketChannel sc = (SocketChannel) event.channel()) {
                        // 创建了一个 1024 字节的缓冲区，用于数据的读取
                        ByteBuffer buf = ByteBuffer.allocate(1024);
                        //这行代码是阻塞的
                        int size = sc.read(buf);
                        if (-1 == size) {
                            sc.close();
                        }
                        String result = new String(buf.array()).trim();
                        ByteBuffer wrap = ByteBuffer.wrap(("PONG:" + result).getBytes(StandardCharsets.UTF_8));
                        sc.write(wrap);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (event.isWritable()) {
                    try (SocketChannel sc = (SocketChannel) event.channel()) {

                    }
                }
                events.remove();
            }
        }
        service.shutdown();
        ssc.close();

    }
}
