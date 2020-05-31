package com.chen.mybatisfactories.aop;

import com.sun.codemodel.internal.JInvocation;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class Demo {
    //如果你对动态代理有过了解了，对下面的代码会很好理解的
    @Test
    public void demo1(){
        //准备好需要被代理的原型对象
        Person p = new Person();
        ProxyFactory factory = new ProxyFactory();//ProxyFactoryBean的功能比ProxyFactory强
        factory.setTarget(p);//给代理工厂一个原型对象

        //构造切面
        //切面=切点 + 通知

        //切点
        JdkRegexpMethodPointcut cut = new JdkRegexpMethodPointcut();
        //cut.setPattern("cn.hncu.javaImpl.Person.run");//可以直接给方法全名
        //或者给正则表达式
        cut.setPattern(".*run.*");//.号匹配除"\r\n"之外的任何单个字符。*号代表零次或多次匹配前面的字符或子表达式
            //通知
            Advice advice = new MethodInterceptor() {
                //哈哈，看到这个是不是和动态代理中的那个方法很像
                @Override
                public Object invoke(MethodInvocation methodInv) throws Throwable {
                    System.out.println("前面拦拦...");
                    Object resObj = methodInv.proceed();//放行
                    System.out.println("后面拦拦...");
                    return resObj;
                }
        };
        //切面 = 切点 + 通知
        Advisor advisor = new DefaultPointcutAdvisor(cut, advice);

        factory.addAdvisors(advisor,advisor);//给代理工厂一个切面
        Person p2 = (Person) factory.getProxy();//从代理工厂中获取一个代理后的对象

        p2.run();
        System.out.println();
        p2.say();//不会拦
        System.out.println();
        p2.run();
    }
}
