package com.chen.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class ShardingJdbcDemoApplication {

    public static void main(String[] args) {
        AbstractApplicationContext abstractApplicationContext;
        SpringApplication.run(ShardingJdbcDemoApplication.class, args);
    }

}
