package com.chen.mybatisfactories.aop.advice;

import com.chen.mybatisfactories.aop.Human;
import com.chen.mybatisfactories.aop.Person;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import java.lang.reflect.Method;

public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("准备资源~");
//        method.invoke(target, args);
    }

    public static void main(String[] args) {
        //准备好需要被代理的原型对象
        Human p = new Person();
        ProxyFactory factory = new ProxyFactory();//ProxyFactoryBean的功能比ProxyFactory强
        factory.setTarget(p);//给代理工厂一个原型对象
        factory.setInterfaces(p.getClass().getInterfaces());
        //构造切面
        //切面=切点 + 通知

        //切点
        JdkRegexpMethodPointcut cut = new JdkRegexpMethodPointcut();
        //cut.setPattern("cn.hncu.javaImpl.Person.run");//可以直接给方法全名
        //或者给正则表达式
        cut.setPattern(".*run.*");//.号匹配除"\r\n"之外的任何单个字符。*号代表零次或多次匹配前面的字符或子表达式
        //通知
        Advice advice = new MyBeforeAdvice();

        //切面 = 切点 + 通知
        Advisor advisor = new DefaultPointcutAdvisor(cut, advice);
        factory.addAdvisors(advisor);//给代理工厂一个切面
        Human p2 = (Human) factory.getProxy();//从代理工厂中获取一个代理后的对象
        p2.toString();
        p2.run();
        System.out.println();
        p2.say();//不会拦
        System.out.println();
        p2.run();
    }
}
