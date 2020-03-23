package com.chen.realproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void loggerLevelTest(){
        logger.info("info日志"+new Date().toString());
        logger.error("info日志"+new Date().toString());
    }


}
