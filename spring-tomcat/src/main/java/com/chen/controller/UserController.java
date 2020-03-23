package com.chen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;

@RestController
public class UserController {
    @RequestMapping("/show")
    public Object show(){
        HashMap hashMap = new HashMap();
        hashMap.put("code",200);
        hashMap.put("msg","ok");
        return  hashMap;
    }
}
