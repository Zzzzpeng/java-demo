package com.chen.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskRunner {
    @Scheduled(cron = "1/0 0 14 * * ?")
    public void run(){
        System.out.println("xxixixi嘻嘻嘻");
    }
}
