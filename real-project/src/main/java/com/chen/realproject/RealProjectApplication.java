package com.chen.realproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.chen.realproject"})
public class RealProjectApplication {

    public static void main(String[] args) {
        AbstractApplicationContext abstractApplicationContext;
        ConfigurableApplicationContext run = SpringApplication.run(RealProjectApplication.class, args);

    }

}
