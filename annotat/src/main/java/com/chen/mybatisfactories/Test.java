package com.chen.mybatisfactories;

import com.chen.mybatisfactories.conf.AppConfig;
import com.chen.mybatisfactories.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService bean = ac.getBean(UserService.class);
        int update = 0;
        try {
             update = bean.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(update);
//        UserMapper bean = SpringContextUtils.getApplicationContext().getBean(UserMapper.class);
//        System.out.println(bean.getOne());
//        MyUserMapper bean1 = SpringContextUtils.getApplicationContext().getBean(MyUserMapper.class);
//        System.out.println(bean1.getOne());
    }
}
