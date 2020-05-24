package com.chen.mybatisfactories.bean;

import com.chen.mybatisfactories.service.GoodService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Order(3)
@Component
public class Bean1 implements InitializingBean, GoodService {
    public Bean1() {
        System.out.println("@Order(3)");
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        //看起来afterPropertiesSet的执行顺序并不是根据@order,@order应该是对org.springframework.boot.CommandLineRunner起作用
        //        org.springframework.boot.CommandLineRunner
        System.out.println("@Order(3)");
    }

    @Override
    public int sale(int id, int num) {
        System.out.println("sale~~~~~~~~~~~~~~~");
        return 0;
    }
}
