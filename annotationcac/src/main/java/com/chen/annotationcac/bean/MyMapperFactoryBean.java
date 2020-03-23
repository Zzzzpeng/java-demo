package com.chen.annotationcac.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class MyMapperFactoryBean implements FactoryBean {

    private Class mapperInterface;

    public MyMapperFactoryBean(){
        super();
    }

    /**
     * 构造方法接受一个对象
     * @param mapperInterface
     */
    public MyMapperFactoryBean(Class mapperInterface){
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return new MySimpleMapper();
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}