package nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.util.CharsetUtil;

import java.util.concurrent.FutureTask;

public class ServerHandlerGroup {
    static class ServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("ServerHandler.channelReadComplete()..");
//            ctx.writeAndFlush(Unpooled.copiedBuffer("resp", CharsetUtil.UTF_8));
            ctx.writeAndFlush(Unpooled.copiedBuffer("only..http..receive\t", CharsetUtil.UTF_8));
            super.channelReadComplete(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            EventLoop eventExecutors = ctx.channel().eventLoop();
//            eventExecutors.execute(() -> {
                System.out.println("ServerHandler.channelRead().....");
                System.out.println(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
//            });
            super.channelRead(ctx, msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("通过回调方法exceptionCaught处理异常...");
            super.exceptionCaught(ctx, cause);
        }
    }

    static class ServerHandler0 extends ChannelInboundHandlerAdapter {
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelReadComplete()..读取完毕....处理业务逻辑...........");
            ctx.writeAndFlush(Unpooled.copiedBuffer("resp", CharsetUtil.UTF_8));
//            super.channelReadComplete(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("channelRead().....");
            System.out.println(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
//            super.channelRead(ctx, msg);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("通过回调方法exceptionCaught处理异常...");
            super.exceptionCaught(ctx, cause);
        }
    }
}