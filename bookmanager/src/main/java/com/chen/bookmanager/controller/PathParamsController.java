package com.chen.bookmanager.controller;

import com.alibaba.fastjson.JSON;
import com.chen.bookmanager.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;
import sun.net.www.http.HttpClient;

import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PathParamsController {

    class Animal implements Comparable<Animal> {
        private int age;

        @Override
        public int compareTo(Animal o) {
            return age - o.age;
        }
    }
    class Dog extends Animal {
    }
    class Humam extends Animal {
    }
    @RequestMapping(value = "/param/{name}/{age}", produces = "application/json")
    public Object getParamsFromPath(@PathVariable String name, @PathVariable String age) {
        AbstractApplicationContext abstractApplicationContext;
        ApplicationContext applicationContext;
        Map map = new HashMap();
        map.put("name", name);
        map.put("age", age);
        return map;
    }

    @RequestMapping("/servlet")
    public void deal(HttpServletRequest request, HttpServletResponse resp) {
        ServletContextListener servletContextListener;
        ContextLoaderListener contextLoaderListener;
        System.out.println();
    }

    @RequestMapping("/ng")
    public String dealWithNg(HttpServletRequest request) {
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s + ":" + request.getHeader(s));
        }

        return "8080 is handling nginx";
    }
    @RequestMapping(value = "jsontest", produces = "application/json")
    public String  getParams(@RequestBody Student student) {
        return JSON.toJSONString(student);
    }

    public static void main(String[] args) throws IOException {
        Student student = new Student();
        student.setMyName("123");
        System.out.println(JSON.toJSONString(student));

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(student);
        System.out.println(s);
        mapper.writeValue(new File("F:\\桌面\\a.json"), student);
        DispatcherServlet dispatcherServlet;
    }

}
