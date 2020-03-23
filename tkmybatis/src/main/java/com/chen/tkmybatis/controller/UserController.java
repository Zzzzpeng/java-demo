package com.chen.tkmybatis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @RequestMapping("/get")
    public Object get(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", "zep");
        return objectObjectHashMap;
    }

    public static void main(String[] args) {
        System.out.println((char)0x1);
    }
}
