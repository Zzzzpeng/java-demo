package nio.nettyhttp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerKeepAliveHandler;
import nio.netty.ServerHandlerGroup;

public class MyHttpChannelInilizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
         ch.pipeline().addLast("myServerDecoder", new HttpServerCodec())
                 .addLast(new HttpServerKeepAliveHandler())
                .addLast("myHandler", new MyHttpChannelHandler());
    }
}