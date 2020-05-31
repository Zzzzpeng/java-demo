package com.chen.mybatisfactories.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Around Advice before method invocation");
        Object o = invocation.proceed();
        System.out.println("Around Advice after method invocation");
        return o;
    }
}
