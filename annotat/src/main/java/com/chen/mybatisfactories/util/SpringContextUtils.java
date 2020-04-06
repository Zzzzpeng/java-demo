package com.chen.mybatisfactories.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public SpringContextUtils() {
        System.out.println("构造SpringContextUtils");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("注入容器上下文");
        SpringContextUtils.this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        System.out.println("返回上下文实例");
        return applicationContext;
    }

}
