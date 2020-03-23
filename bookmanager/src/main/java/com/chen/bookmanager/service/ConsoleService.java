package com.chen.bookmanager.service;

import com.chen.bookmanager.model.Student;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {
    public String pr(Student student){
        System.out.println(student.toString());
        System.out.println("输出控制台服务");
        return student.getName();
    }
}
