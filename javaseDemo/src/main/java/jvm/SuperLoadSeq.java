package jvm;

public class SuperLoadSeq {
    public SuperLoadSeq() {
        System.out.println("super构造");
    }
    static LoadTest staticFiled = new LoadTest("super static filed");
    LoadTest filed = new LoadTest("super normal filed");

    static {
        System.out.println("super static code");
    }
    {
        System.out.println("super code");
    }
}
