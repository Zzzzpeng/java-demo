package proxy;

import java.lang.reflect.Proxy;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        test();
    }
    static void normalProxy(){
        ProxyHander proxyHander = new ProxyHander();
//        proxyHander.setTarget(new Host());

//        host.rent();
//        host.takeFee();
        Rent host = (Rent) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Rent.class}, new ProxyHander());
        host.toString();
    }
    static void test(){
        Host host = new Host();
        host.rent();
    }
}
