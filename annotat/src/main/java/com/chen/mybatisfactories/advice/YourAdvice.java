package com.chen.mybatisfactories.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class YourAdvice  implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before method invocation");
        Object o = invocation.proceed();
        System.out.println("after method invocation");
        return o;
    }
}
