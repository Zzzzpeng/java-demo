package com.chen.mybatisfactories.conf;

import com.chen.mybatisfactories.bean.EngineFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class EngineConfigScanner implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(EngineFactoryBean.class);
        gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
        gbd.setAutowireCandidate(true);
        bdr.registerBeanDefinition("engine", gbd);
    }
}
