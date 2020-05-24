package com.chen.mybatisfactories.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class Bean2 implements InitializingBean {
    public Bean2() {
        System.out.println(":@Order(2)");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(":@Order(2)");
    }
}
