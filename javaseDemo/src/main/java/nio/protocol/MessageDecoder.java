package nio.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MessageDecoder.decode()...was call");
        int len = in.readInt();
        byte[] data = new byte[len];
        in.readBytes(data);
        Message message = new Message();
        message.setLen(len);
        message.setContent(data);
        out.add(message);
    }
}
