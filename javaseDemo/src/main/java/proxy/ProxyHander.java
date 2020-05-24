package proxy;

import com.sun.deploy.net.proxy.ProxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHander implements InvocationHandler {
    private Object target ;

    public void setTarget(Object target) {
        this.target = target;
    }

    public  <T> T getProxy(Class<? extends T> clz ){
        if(clz==null) throw new RuntimeException("传入的Class类型为空");
        if(target == null) throw new RuntimeException("没有代理对象");
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(), ProxyHandler.class.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行"+method.getName()+"方法");
        return method.invoke(target,args);
    }
}
