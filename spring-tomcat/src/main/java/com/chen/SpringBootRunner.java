package com.chen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.chen.realproject"})
//@EnableApolloConfig
//@PropertySource("classpath:application.yml")
public class SpringBootRunner {


    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(SpringBootRunner.class, args);
    }
}