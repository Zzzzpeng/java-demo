package com.chen.realproject.controller;

import com.chen.realproject.entity.Article;
import com.chen.realproject.message.covert.TestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/json")
public class JsonController {
    @RequestMapping(value = "/test")
    public Object getJson(){
        return new TestDTO(new BigDecimal("6.6"));
    }

    @RequestMapping("/get")
    public Object get(){
        return new Article();
    }
}
