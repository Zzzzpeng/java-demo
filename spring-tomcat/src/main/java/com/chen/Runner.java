package com.chen;

import com.chen.service.Iter;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.util.Iterator;
import java.util.ServiceLoader;

public class Runner {
    public static void main(String[] args) {
        ServiceLoader<Iter> loader = ServiceLoader.load(Iter.class);
        Iterator<Iter> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Iter next = iterator.next();
            System.out.println(next);
        }
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        try {
            tomcat.addWebapp("/", "f:/webapp");
            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class IterImpl implements Iter {

    }
    static class IterImplB implements Iter{

    }
}
