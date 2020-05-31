package com.chen.mybatisfactories.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
* AOP可以说是OOP（面向对象编程）的补充和完善。
在OOP设计中有可能导致代码的重复不利于模块的重用性，例如日志功能。
日志代码往往水平地散布在所有对象层次中，而与它所散布到的对象的核心功能关系不大。
但是在OOP中这些业务要和核心业务代码在代码这一级集成。
还有些如安全性、事务等也是如此。
能不能把这些与核心业务无关但系统中需要使用的业务（称为切面）单独编写成一个模块，
* 在主要核心业务代码中不调用，而是在配置文件中做些配置，配置核心业务需要使用到得切面部分，
* 在系统编译时才织入到业务模块中。

切面（Aspect）：
简单的理解就是把那些与核心业务无关的代码提取出来，进行封装成一个或几个模块用来处理那些附加的功能代码。
（如日志，事务，安全验证）我们把这个模块的作用理解为一个切面。
其实切面就是我们写一个类，这个类中的代码原来是在业务模块中完成的，现在单独成一个或几个类。在业务模块需要的时候才织入。

织入（Weaving）：
把切面（aspect）连接到其它的应用程序类型或者对象上，并创建一个被通知（advised）的对象。
*  这些可以在编译时，类加载时和运行时完成。Spring和其它纯Java AOP框架一样，在运行时完成织入。

切入点（Pointcut）：
也就是切点。
本质上是一个捕获连接点的结构。在AOP中，可以定义一个pointcut，来捕获相关方法的调用

通知（Advice）：
在切面的某个特定的连接点（Joinpoint）上执行的动作。通知有各种类型，其中包括“around”、“before”和“after”等通知。
通知的类型将在后面部分进行讨论。许多AOP框架，包括Spring，都是以拦截器做通知模型，并维护一个以连接点为中心的拦截器链
*/
public class DeafultAdvisor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("MethodInterceptor..before");
        Object proceed = invocation.proceed();
        System.out.println("MethodInterceptor..after");
        return proceed;
    }
}
