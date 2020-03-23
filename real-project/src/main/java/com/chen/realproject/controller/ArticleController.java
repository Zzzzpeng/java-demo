package com.chen.realproject.controller;

import com.chen.realproject.data.RespMap;
import com.chen.realproject.entity.Article;
import com.chen.realproject.service.IArticleService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {
    @Resource
    IArticleService iArticleService;

    @GetMapping
    Map handle() {
        Map map = new HashMap();
        map.put("code", 200);
        map.put("data", null);
        return map;
    }

    @RequestMapping("/findOne")
    public RespMap findOne(@RequestParam long id) {
        RespMap respMap;
        try {
            Object article = iArticleService.findOne(id);

            respMap = new RespMap();
            respMap.setData(article);
        } catch (Exception e) {
            respMap = new RespMap("500", e.toString());
        }
        return respMap;
    }

    @RequestMapping("/findAll")
    public RespMap findAll(int pageNum, @Max(value = 3, message = "不能大于99岁") int pageSize) {
        if (pageSize == 2) throw new RuntimeException("不能等于2!");
        RespMap respMap;

        try {
            Object list = iArticleService.findList(pageNum, pageSize);
            respMap = new RespMap();
            respMap.setData(list);
        } catch (Exception e) {
            respMap = new RespMap("500", e.toString());
            e.printStackTrace();
        }
        return respMap;
    }

    @PostMapping
    public RespMap createArticle(@RequestBody @Valid @RequestParam Article article) {
        System.err.println("处理createArticle请求");
        System.out.println("参数:" + article);
        BigDecimal bigDecimal = BigDecimal.valueOf(2.2d);
        return new RespMap();
    }


//    @GetMapping
//    public RespMap findOne1( long id) {
//        RespMap respMap;
//        try {
//            Article article = iArticleService.findOne(id);
//            respMap = new RespMap();
//            respMap.setData(article);
//        } catch (Exception e) {
//            respMap = new RespMap("500", e.toString());
//        }
//        return respMap;
//    }


}
