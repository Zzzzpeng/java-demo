package com.chen.realproject.controller;

import com.chen.realproject.entity.Article;
import com.chen.realproject.message.covert.TestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@RestController()
@RequestMapping("/json")
public class JsonController {

    @RequestMapping(value = "/test")
    public Object getJson(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //  这里最好明确的写允许的域名
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization,ybg");
        Cookie cookie = new Cookie("name","czp");
//        cookie.setDomain("www.baidu.com");
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        return new TestDTO(new BigDecimal("6.6"));
    }

    @RequestMapping("/get")
    public Object get(){
        return new Article();
    }
}
