package com.chen.realproject.controller;

import com.chen.realproject.data.RespMap;
import com.chen.realproject.load.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggerController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Test.Inner inner;

    @GetMapping
    public RespMap errorLog(){
        logger.error("error级别日志");
        return new RespMap();
    }
    @GetMapping("/info")
    public RespMap infoLog(){
        logger.info("info级别日志");
        return new RespMap();
    }

    @GetMapping("test")
    public RespMap test(@RequestParam int age){
        return new RespMap();
    }


}
