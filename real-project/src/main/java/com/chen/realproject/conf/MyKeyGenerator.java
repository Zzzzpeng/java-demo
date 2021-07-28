package com.chen.realproject.conf;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("MyKeyGenerator")
public class MyKeyGenerator implements KeyGenerator {


    @Override
    public Object generate(Object target, Method method, Object... params) {
        //返回后缀名
        //return method.getName();

        //注意，这里不能返回null,否则会报错
        //java.lang.IllegalArgumentException:
        // Null key returned for cache operation (maybe you are using named params on classes without debug info?) Builder[public java.util.List com.wbg.springRedis.service.impl.RoleServiceImpl.listAll()] caches=[listAll] | key='' | keyGenerator='myKeyGenerator' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'
        return "";
    }
}
