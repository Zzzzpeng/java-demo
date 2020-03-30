package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个channel同时使用多个buffer同时
 */
public class MutiBufferTest {
    public static void main(String[] args) throws IOException {
        new Thread(()->{
            try {
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8080));
                SocketChannel socketChannel = serverSocketChannel.accept();
                ByteBuffer[] byteBuffers = new ByteBuffer[5];
                for (int i = 0; i < byteBuffers.length; i++) {
                    byteBuffers[i] = ByteBuffer.allocate(2);
                }
                socketChannel.read(byteBuffers);

                for (ByteBuffer byteBuffer : byteBuffers) {
                    byteBuffer.flip();
                    System.out.println(byteBuffer);
                    System.out.println(new String(byteBuffer.array()).length());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("127.0.0.1",8080));
                socket.getOutputStream().write("hel".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
