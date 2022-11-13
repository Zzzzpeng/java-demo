package com.chen.mybatisfactories.bean;

import com.chen.mybatisfactories.service.Engine;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class EngineFactoryBean implements FactoryBean<Engine>,  InvocationHandler {
    @Override
    public Engine getObject() {
        return (Engine) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{Engine.class},this);
    }
    @Override
    public Class<?> getObjectType() {
        return Engine.class;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("toString"))
            return toString();
        System.out.println(method.getName()+"方法被执行............EngineFactoryBean代理执行方法-----------");
        return null;
    }
    @Override
    public String toString() {
        return "EngineFactoryBean@" + hashCode();
    }
}

