package nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ChannalTest {
    public static void main(String[] args) throws Exception {

        RandomAccessFile aFile = new RandomAccessFile("F:\\桌面\\pwd.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer bytebuffer = ByteBuffer.allocate(48);
        List<Byte> byteList = new ArrayList<>();
        while((inChannel.read(bytebuffer)) != -1) {
//            System.out.println("Read " + bytesRead);
            bytebuffer.flip();
            while (bytebuffer.hasRemaining()) {
                byteList.add(bytebuffer.get());
//                System.out.println(new String(bytebuffer.array()));
            }
            bytebuffer.clear();
        }

        aFile.close();
        byte[] res = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            res[i] = byteList.get(i);
        }
        System.out.println(new String(res, "gbk"));
    }
}
