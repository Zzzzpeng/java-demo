package com.chen.hibernatedemo.hibernatedemo;

import com.chen.hibernatedemo.hibernatedemo.dao.PostRepository;
import com.chen.hibernatedemo.hibernatedemo.model.GroupPost;
import com.chen.hibernatedemo.hibernatedemo.service.GroupPostService;
import com.chen.hibernatedemo.hibernatedemo.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//用于扫描Dao层@Repository
@EnableJpaRepositories(basePackages = "com.chen.hibernatedemo.hibernatedemo.dao")
//用于扫描JPA实体类 @Entity
@EntityScan(basePackages = "com.chen.hibernatedemo.hibernatedemo.model")
@SpringBootApplication
public class HibernatedemoApplication {
    public static void main(String[] args) throws JsonProcessingException {
        ConfigurableApplicationContext ac = SpringApplication.run(HibernatedemoApplication.class, args);
        TestService testService = ac.getBean(TestService.class);
        testService.test();
    }

}
