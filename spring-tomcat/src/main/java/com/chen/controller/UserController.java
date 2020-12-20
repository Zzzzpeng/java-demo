package com.chen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@RestController
public class UserController {
    @RequestMapping("/show")
    public Object show(HttpServletResponse response) throws IOException {
        HashMap hashMap = new HashMap();
        hashMap.put("code",200);
        hashMap.put("msg","ok");
        response.getWriter().write(hashMap.toString());
        response.getWriter().flush();
        response.getWriter().close();
        return null;
    }
}
