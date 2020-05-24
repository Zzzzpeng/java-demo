package nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        startServer();
    }
    public static void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandlerGroup.ServerHandler());
//                                    .addLast(new ServerHandlerGroup.ServerHandler0());
                        }
                    });
            System.out.println("server..get ready");
            ChannelFuture cf = serverBootstrap
                    .bind("localhost",8080);
            cf.addListener(future -> System.out.println("first:  " + future));
            cf.addListener(future
                    -> System.out.println("second:  " + future));
            cf.channel().closeFuture().sync();
        }catch (Exception e){
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
