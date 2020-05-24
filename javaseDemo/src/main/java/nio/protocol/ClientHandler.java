package nio.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    //通道就绪时回调该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Message message = new Message();
        String msg = "我想拍啥包";
        byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
        message.setContent(bytes);
        message.setLen(bytes.length);
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(message);
        }
    }

    //有读事件时回调该方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message = (ByteBuf) msg;
        System.out.println("client收到msg:"+message.toString(CharsetUtil.UTF_8));
    }
}
