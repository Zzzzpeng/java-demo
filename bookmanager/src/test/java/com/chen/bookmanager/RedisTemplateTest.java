package com.chen.bookmanager;

import com.chen.bookmanager.jetcache.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.BitSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;
    @Test
    public void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.setBit("#0223", 0, true);
        valueOperations.setBit("#0223", 1, true);
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        Long count = connection.bitCount("#0223".getBytes());
        System.out.println(count);

//        System.out.println(redisTemplate.getseri);
//        ValueOperations valueOpt = redisTemplate.opsForValue();
//        String key = "size";
//        for (int i = 0; i < 10; i++) {
//            redisTemplate.delete(key);
//            Boolean notExist = valueOpt.setIfAb起床咯1582438772sent(key, "12", 15, TimeUnit.MINUTES);
//            System.out.println(notExist);
//        }

//        for (int i = 0; i < 10; i++) {
//            userService.getUserById(123L,"茜茜");
//        }
//        userService.delete(123L,"茜茜");

//        Long incre_key = valueOpt.increment("incre_key",555);
//        System.out.println(incre_key);

    }
}
