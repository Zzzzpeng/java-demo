package com.chen.bookmanager;

import com.chen.bookmanager.service.ConsoleService;
import com.sun.org.apache.xpath.internal.functions.FuncTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmanagerApplicationTests {
    @Autowired
    private ConsoleService consoleService;

    @Autowired
    RedisTemplate redisTemplate;


    static String REDIS_KEY = "mq_id";


//    @Autowired
//    ExecutorService executorService;


    @Test
    public void redisSenTest() {
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForHash();
    }


    @Test
    public void contextLoads() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
//        Executors.newCachedThreadPool();
//        System.out.println(redisTemplate.opsForHash().get(REDIS_KEY,"Credit_001"));
        Callable runnable = new Callable() {
            @Override
            public Object call() {
                HashOperations hashOperations = redisTemplate.opsForHash();
                Boolean exist = hashOperations.putIfAbsent(REDIS_KEY, "Credit_001", "1");
//                System.out.println("???");
                System.out.println(exist);
                new ArrayList().remove(1);
                return exist;
            }
        };
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Future<?> submit = executorService.submit(runnable);
            futureList.add(submit);
        }
        System.out.println("输出结果: ****************************");
        for (Future future : futureList) {
            System.out.println(future.get() + " xx");
        }
    }

    @Test
    public void redisConCurrentTest() {
//        for (int i = 0; i < 50; i++) {
        String num = Integer.toString(0);
        HashOperations hashOperations = redisTemplate.opsForHash();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Boolean mq_id = hashOperations.putIfAbsent(REDIS_KEY, "Credit_001", "01");
                System.out.println(mq_id);
            }
        }).start();
//        }
    }

    @Test
    public void shutdownNowTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("倒计时" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executorService.execute(runnable);
        Thread.sleep(3000);
        executorService.shutdownNow();
    }

    public void redisTest(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
    }


}
