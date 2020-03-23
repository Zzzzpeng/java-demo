package com.chen.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.DispatcherServlet;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@MapperScan("com.chen.tkmybatis.dao")
public class TkmybatisApplication {
    DispatcherServlet dispatcherServlet;

//    public static void main(String[] args) {
//        SpringApplication.run(TkmybatisApplication.class, args);
//    }
    public static void main(String[] args) throws IOException {
//        System.out.println(1<<31);
//        System.out.println(0x7fffffff);
//        long time = 0x7fffffff * 1000;
//        System.out.println(new Date(time));
//        System.out.println((time+"").length());

//        insertGroup2ContentId();


       insertPost();
    }

    public static void insertPost() throws IOException {
        long start = System.currentTimeMillis();
        BufferedWriter writerGroup = new BufferedWriter(new FileWriter(new File("D:/tbPost.txt"), true));
        int contentId = 0;
        Random random = new Random();
        for (int i = 0; i < 5000 * 10000; i++) {
                writerGroup.write(contentId +"\t"
                        + "你谁"+i +"\t"
                        + "主"+i +"\t"
                        + "[]" +"\t"
                        + i +"\t"
                        + "0" +"\t"
                        + "0" +"\t"
                        +(start/1000 - random.nextInt(3600 * 24 * 365 * 2))+"\n");  //两年内
                contentId++;
        }
        writerGroup.flush();
        writerGroup.close();
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void insertGroup2ContentId() throws IOException {
        long start = System.currentTimeMillis();
        Random random = new Random();
        BufferedWriter writerGroup = new BufferedWriter(new FileWriter(new File("D:/tbGroupPost.txt"), true));
        int contentId = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 1000 * 10000; j++) {
                writerGroup.write(i + "\t" + contentId++ + "\t" + random.nextInt(2)+"\n");
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        writerGroup.flush();
        writerGroup.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
