package proxy;

import com.sun.deploy.net.proxy.ProxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Host implements Rent {
    Rent proxyHander;

    public Host() {
        proxyHander =  (Rent)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Rent.class},new ProxyHander());
    }

    @Override
    public void takeFee() {
        proxyHander.takeFee();
        System.out.println("收取房租");
    }

    @Override
    public void rent() {
        proxyHander.rent();
        System.out.println("出租房屋");
    }

    private class ProxyHander implements InvocationHandler {
        public  <T> T getProxy(Class<? extends T> clz ){
            return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),Host.class.getInterfaces(),this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("执行"+method.getName()+"方法");
            return method.invoke(Host.this,args);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Host.class.getInterfaces()));
    }
}
