package com.chen.mybatisfactories.aop;

import java.util.Arrays;

public class Person implements Human{

    @Override
    public void run() {
        System.out.println("Person run run run");
    }
    @Override
    public void say(){
        System.out.println("say say say");
    }
}
