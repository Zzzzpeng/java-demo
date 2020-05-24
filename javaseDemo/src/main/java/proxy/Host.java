package proxy;

import com.sun.deploy.net.proxy.ProxyHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Host implements Rent,Human {

    @Override
    public void takeFee() {
        System.out.println("收取房租");
    }

    @Override
    public void rent() {
        System.out.println("出租房屋");
    }

    @Override
    public void eat() {
        System.out.println("吃东西");
    }
}
