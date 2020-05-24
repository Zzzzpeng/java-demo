package proxy;

import java.lang.reflect.Proxy;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

//        normalProxy();
    }
    static void normalProxy(){
        ProxyHander proxyHander = new ProxyHander();
        proxyHander.setTarget(new Host());


        Rent host = (Rent) Proxy.newProxyInstance(Test.class.getClassLoader(),Host.class.getInterfaces(), proxyHander);
        host.takeFee();
        ((Human) host).eat();
//        host.toString();
    }
}
