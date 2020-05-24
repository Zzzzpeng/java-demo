package jvm;

import java.util.Collections;

public class LoadSeq extends SuperLoadSeq{
    public LoadSeq() {
        System.out.println("构造");
    }
    static LoadTest staticFiled1 = new LoadTest("static filed");
    LoadTest filed1 = new LoadTest("normal filed");

    static {
        System.out.println("static code");
    }
    {
        System.out.println("code");
    }
    static void show(){
        System.out.println("static method");
    }
    //静态变量、静态代码块、成员变量、实例代码块、构造方法
}
