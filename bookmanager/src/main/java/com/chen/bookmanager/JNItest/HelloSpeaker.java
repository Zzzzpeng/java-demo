package com.chen.bookmanager.JNItest;

import java.util.concurrent.ConcurrentHashMap;

public class HelloSpeaker {
    public native void helo(String name);

    static {
        System.loadLibrary("hello");
    }

    public static void main(String[] args) {
        new HelloSpeaker().helo("zzzzpeng");
    }
}
