package com.chen.hibernatedemo.hibernatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//用于扫描Dao层@Repository
@EnableJpaRepositories(basePackages = "com.chen.hibernatedemo.hibernatedemo.dao")
//用于扫描JPA实体类 @Entity
@EntityScan(basePackages = "com.chen.hibernatedemo.hibernatedemo.model")
@SpringBootApplication
public class HibernatedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatedemoApplication.class, args);
    }

}
