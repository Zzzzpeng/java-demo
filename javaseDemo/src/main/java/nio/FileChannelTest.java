package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    static void fileWrite() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("f://nio.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("陈泽鹏你好".getBytes());
        buffer.flip();
        channel.write(buffer);

    }
    static void fileRead() throws IOException {
        File file = new File("f://nio.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        byteBuffer.put("陈泽鹏你好".getBytes());
        byteBuffer.flip();
        int read = channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        byteBuffer.clear();
    }
    static void fileTransfer() throws IOException {
        File file = new File("f://nio.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream("f://nio_cp.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = out.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
        fileInputStream.close();
        out.close();
//        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
//        byteBuffer.put("陈泽鹏你好".getBytes());
//        byteBuffer.flip();
//        int read = inChannel.read(byteBuffer);
//        System.out.println(new String(byteBuffer.array()));
//        byteBuffer.clear();
    }
    static void byfferTest(){
        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.putInt(1);
        buffer.flip();
        buffer.getInt();
//        buffer.getInt();
        System.out.println(1);
    }
    public static void main(String[] args) throws IOException {
//        fileRead();
//        fileTransfer();
        byfferTest();
    }
}
