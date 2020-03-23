package com.chen.annotationcac.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserMapperInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        System.out.println("*********执行db操作***********");
        return null;
    }
}
