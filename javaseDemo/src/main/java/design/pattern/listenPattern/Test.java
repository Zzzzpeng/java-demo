package design.pattern.listenPattern;

public class Test {
    public static void main(String[] args) {
        Kid kid = new Kid();
        kid.addIWListener(event -> System.out.println(event.getSource()));
        kid.eat();

        IoBootstrap ioBootstrap = new IoBootstrap();
        ioBootstrap.addMsgListenner(msg -> System.out.println("监听到右消息发给IoBootstrap,内容:" + msg));
        ioBootstrap.process("客户端请求连接...");
    }
}
