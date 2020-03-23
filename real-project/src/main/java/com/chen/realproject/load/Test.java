package com.chen.realproject.load;

import com.chen.realproject.controller.ArticleController;
import com.chen.realproject.util.SpringContextUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

public class Test {
    public static Son son = new Son();
    public static Fa fa = new Fa();

    public interface Inner{
        public static final ArticleController articleController = SpringContextUtils.getApplicationContext().getBean(ArticleController.class);
    }

}
   interface Inner{
    public static final ArticleController articleController = SpringContextUtils.getApplicationContext().getBean(ArticleController.class);
}
class Fa{
    public Fa() {
        System.out.println("fa构造");
    }
}
class Son {
    public Son() {
        System.out.println("Son构造");
    }
}