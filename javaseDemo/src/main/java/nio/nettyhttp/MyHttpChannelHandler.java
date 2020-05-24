package nio.nettyhttp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import sun.invoke.empty.Empty;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyHttpChannelHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            System.out.println(((HttpRequest) msg).uri());
            ctx.channel().eventLoop().schedule(()->{
                System.out.println(new Date());
                System.out.println("channelRead0处理http请求,pipeline:"+ctx.pipeline().hashCode());
                System.out.println("客户端adress:"+ctx.channel().remoteAddress());
                ByteBuf byteBuf = Unpooled.copiedBuffer("来自netty服务器的响应!!", CharsetUtil.UTF_8);
                DefaultFullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
                resp.headers().add(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf8");
                resp.headers().add(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
                ctx.writeAndFlush(resp);
            },5, TimeUnit.SECONDS);
        }else {
            ctx.writeAndFlush("only http receive".getBytes("utf8"));
        }
    }
}
