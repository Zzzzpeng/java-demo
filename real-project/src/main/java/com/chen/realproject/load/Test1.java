package com.chen.realproject.load;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test1 {
//    @Scheduled(cron = "*/5 * * * * ?")
    public void show(){
        System.out.println("定时打印");
    }
}
