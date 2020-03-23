package com.chen.bookmanager.schedule;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

@Component
public class GetUpTask {
    @Scheduled(cron = "0/1 * * * * ?")
    public void getUp(){
        System.out.println("起床咯"+System.currentTimeMillis()/1000);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName()+" ----- error");
        }
    }
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void getUpOne(){
//
//        System.out.println("赖床咯"+System.currentTimeMillis()/1000);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            System.err.println(Thread.currentThread().getName()+" ----- error");
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        BeanDefinition beanDefinition;
        GenericBeanDefinition genericBeanDefinition;
//        genericBeanDefinition
        String[] names = {"你","我","他","ta"};
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(3,3,0,TimeUnit.MILLISECONDS,
                                new SynchronousQueue<>());
        List<Future> futureList = new ArrayList<>();
        for (String name : names) {
            System.out.println(name);
            Future<String> future = null;
            try {
                future = executorService.submit(new Task(name));
            } catch (Exception e) {
                e.printStackTrace();
            }
            futureList.add(future);
        }
        futureList.forEach(item-> {
            try {
                System.out.println(item.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();

    }
    static class Task implements Callable<String>{
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            System.out.println(name+"--开始执行");
            int i = new Random().nextInt(5);
            Thread.sleep(i*1000);
            System.out.println(name+"--执行完毕,时间"+i);
            return name+i ;
        }
    }
}
