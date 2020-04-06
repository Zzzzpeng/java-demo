package com.chen.annotationcac.bean;

import com.chen.annotationcac.dao.UserMapper;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;


public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 注册BeanDefinition
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        /**
         * 这里我们可以通过beanDefinitionRegistry 来注册一个BeanDefinition,但是这里我们不能先通过
         * Class[] clazz = new Class[]{UserMapper.class};
         * UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Client.class.getClassLoader(), clazz, new UserMapperInvocationHandler());
         * 拿到代理对象，因为这样代理对象已经生成了，所以我们想到了用FactoryBean,我们直接注册MyMapperFactoryBean
         */
//        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MyMapperFactoryBean.class);
//        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getBeanDefinition();
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
//        //这里我们可以通过Mybatis的包扫描@MapperScan拿到所有的mapper然后拿到他的类名替代这个userMapper就行了
//        beanDefinitionRegistry.registerBeanDefinition("userMapper",beanDefinition);
        System.out.println("sss");
    }
}