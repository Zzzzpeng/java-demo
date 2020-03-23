package com.chen;


import com.chen.excelbean.Runner;
import com.chen.mail.SendMailException;
import com.chen.mail.SendMailTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.chen")
public class Main {
        public final static String FROM_FILE_PATH = "/home/excel-task/line.xlsx";
        public final static String TO_FILE_PATH = "/home/excel-task/finish.xlsx";

//    public final static String FROM_FILE_PATH = "F:\\桌面\\pack\\票据利率曲线.xlsx";
//    public final static String TO_FILE_PATH = "F:\\桌面\\pack\\finish.xlsx";

    public final static String MIAL_TITLE = "票据利率曲线";
    public static void main(String[] args) throws SendMailException {
        SpringApplication.run(Main.class);
//        try {
//            new Runner().updateFile();
//            new SendMailTask().initAndSend();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
