package com.chen.mybatisfactories.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class Bean3 implements InitializingBean {
    public Bean3() {
        System.out.println("@Order(1)");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("@Order(1)");
    }
}
