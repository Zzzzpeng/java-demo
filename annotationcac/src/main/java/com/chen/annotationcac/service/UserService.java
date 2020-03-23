package com.chen.annotationcac.service;

import com.chen.annotationcac.dao.UserMapper;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class UserService {
//    @Autowired
    UserMapper userMapper;
    @Resource
    public NameService nameService;

    public UserService() {
        System.out.println("UserService construct");
    }
//    public void getAll(){
//        Map postById = userMapper.getPostById(2);
//        System.out.println(postById);
//    }
}
