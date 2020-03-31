package nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

public class Test {
    static int i = 9989;
    public static void main(String[] args) throws IOException, InterruptedException {
//        runServer();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        runNioServer();
//        executorService.execute(()->runClient(5000));
//        Thread.sleep(20);
//        executorService.execute(()->runClient(0));
//        executorService.shutdown();
//        runClient(500);
//        start();
        new Thread(()->start()).start();
        new Thread(()->start()).start();

    }

    static void runNioServer() throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();
        new Thread(() -> {
            try {
                // 对应IO编程中服务端启动
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(8000));
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                //select()去检测是否有新连接建立
                //selectedKeys()返回连接集合;如果Acceptable,将连接注册到clientSelector上(isAcceptable什么时候返回否?)

                while (true) {
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                    if (serverSelector.select(1) > 0) {
                        System.out.println("********有新连接************");
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if (key.isAcceptable()) {
                                try {
                                    // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }

                        }
                    }
                }
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isReadable()) {
                                System.out.println("isReadable");
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    // (3) 面向 Buffer
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
                                            .toString());
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }

                        }
                    }
                }
            } catch (IOException ignored) {
            }
        }).start();
    }

    static void runClient(long time) {
        new Thread(() -> {
            try (SocketChannel socketChannel = SocketChannel.open()) {
                socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.flip();
                socketChannel.write(ByteBuffer.wrap("嘻嘻嘻".getBytes()));
                while (true) {
                    Thread.sleep(time);
                    buffer.clear();
                    socketChannel.read(buffer);
                    System.out.println("客户端" + socketChannel + "收到消息: " + new String(buffer.array()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    static void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        // (1) 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // (3) 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }
                            System.out.println("*****************读取完毕***********");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public static void start() {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.bind(new InetSocketAddress("127.0.0.1", 9989+i++));
            //连接服务端socket
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
            socketChannel.connect(socketAddress);
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            int sendCount = 0;
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                int select = selector.select(200);
                if (select > 0) {
                    System.out.println("select > 0");
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            SocketChannel channel = (SocketChannel) key.channel();
//                            channel.configureBlocking(false);
                            buffer.clear();
                            channel.read(buffer);
                            System.out.println("客戶端读取数据:" + new String(buffer.array()));
                        }

                    }
                }
            }

            //这里最好使用selector处理   这里只是为了写的简单
//            while (sendCount < 10) {
//                buffer.clear();
//                //向服务端发送消息
//                buffer.put(("current time : " + System.currentTimeMillis()).getBytes());
//                //读取模式
//                buffer.flip();
//                socketChannel.write(buffer);
//                buffer.clear();
//
//                //从服务端读取消息
//                System.out.println("2222");
//                int readLenth = socketChannel.read(buffer);
//                System.out.println("333");
//                //读取模式
//                buffer.flip();
//                byte[] bytes = new byte[readLenth];
//                buffer.get(bytes);
//                System.out.println(new String(bytes, "UTF-8"));
//                buffer.clear();
//                sendCount++;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
