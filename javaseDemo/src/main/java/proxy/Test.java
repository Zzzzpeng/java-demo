package proxy;

import java.lang.reflect.Proxy;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        normalProxy();
    }
    static void normalProxy(){
        ProxyHander proxyHander = new ProxyHander();
        Host target = new Host();
        proxyHander.setTarget(target);


        Rent host = (Rent) Proxy.newProxyInstance(Test.class.getClassLoader(),target.getClass().getInterfaces(), proxyHander);
        host.takeFee();
        ((Human) host).eat();
//        host.toString();
    }
}
