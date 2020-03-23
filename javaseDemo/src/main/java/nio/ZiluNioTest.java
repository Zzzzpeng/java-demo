package nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZiluNioTest {

    public static class IoClient {
        public void sendMsg(String msg) {
            try {
                Socket socket = new Socket();
//                socket.bind(new InetSocketAddress(8888));
                socket.connect(new InetSocketAddress(8080));
                if (msg != null) {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(msg.getBytes());
                    outputStream.flush();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> new BioServer().listen_0()).start();
//        new Thread(() -> new IoClient().sendMsg(null)).start();
//        Thread.sleep(50);
        new Thread(() -> new IoClient().sendMsg("发一条msg过去")).start();

    }

    public static class BioServer {
        private List<Socket> socketList = new LinkedList<>();

        public void listen_0() {
            try {
                byte[] bytes = new byte[1024];
                ServerSocket serverSocket = new ServerSocket();
                serverSocket.bind(new InetSocketAddress(8080));
                while (true) {

                    //阻塞
//                    serverSocket.setBlock(false);//bio没有这种api
                    Socket socket = serverSocket.accept();
                    System.out.println("accept");

                    for (Socket socketItem : socketList) {
//                        socketItem.setBlock(false);//bio没有这种api
                        int read = socket.getInputStream().read(bytes);
                        if (read == -1) {
                            socketList.remove(socketItem);
                        }
                        String msg = new String(bytes);
                        System.out.println("read = " + read);
                        System.out.println(msg);
                    }
                    //阻塞
                    if (socket != null) {
                        socketList.add(socket);
                    } else {
                        //读-处理socket数据
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class NioServer {
        private List<Socket> socketList = new LinkedList<>();

        public void listen_0() {
            List<SocketChannel> socketChannelList = new ArrayList<>();
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byte[] bytes = new byte[1024];
                ServerSocketChannel serverSocket = ServerSocketChannel.open();
                serverSocket.bind(new InetSocketAddress(8080));
                serverSocket.configureBlocking(false);
                while (true) {
                    for (SocketChannel socketChannel : socketChannelList) {
                        //读数据,-1删除
                    }
                    SocketChannel accept = serverSocket.accept();
                    if (accept != null) {
                        accept.configureBlocking(false);
                        socketChannelList.add(accept);
                        int byteCount = accept.read(byteBuffer);
                        if (byteCount > 0) {
                            System.out.println("read data:  "+byteBuffer.toString());
                        }
                    }
                    else{

                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
