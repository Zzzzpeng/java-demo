package com.chen.realproject.controller;


import com.chen.realproject.service.RoutingDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/dis")
public class DispatcherController {
    @Autowired
    private RoutingDelegate router;

    @RequestMapping("/for")
    public Object handle(HttpServletRequest request, HttpServletResponse response) {
        String target = request.getHeader("target");
        if(!StringUtils.isEmpty(target)){
            ResponseEntity<String> responseEntity = router.redirect(request, response, target, request.getRequestURI());
            return new HashMap(){{
                put("detail", responseEntity);}};
        }
        Map map = new HashMap();
        map.put("code", 500);
        map.put("msg", "header 'target' cannot be null");
        return map;
    }
}
