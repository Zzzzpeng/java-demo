package com.chen.realproject.controller;

import com.chen.realproject.data.RespMap;
import com.chen.realproject.entity.Article;
import com.chen.realproject.service.IArticleService;
import com.chen.realproject.util.SpringContextUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
    Map handle(HttpServletRequest request) {
        Map map = new HashMap();
        map.put("code", 200);
        RedisTemplate bean = SpringContextUtils.getApplicationContext().getBean(StringRedisTemplate.class);
        System.out.println(bean.opsForValue().get("url2Role"));
        map.put("data", iArticleService.getData());
        return map;
    }

    @RequestMapping("/findOne")
    public RespMap findOne() {
        RespMap respMap;
        try {
            Object article = iArticleService.findOne();

            respMap = new RespMap();
            respMap.setData(article);
        } catch (Exception e) {
            respMap = new RespMap("500", e.toString());
        }
        return respMap;
    }

    @RequestMapping("/down")
    public void down(HttpServletResponse resp) throws IOException {
        File file = new File("F:/桌面/syn.png");
        FileInputStream in = new FileInputStream(file);
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "inline;fileName=" + file.getName());
        resp.setHeader("Content-type", "image/png");
        resp.setContentLengthLong(file.length());
        byte[] bf = new byte[1024];
        int len;
        while ((len = in.read(bf)) != -1) {
            out.write(bf, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

    public static void main(String[] args) {
        System.out.println("<html>welcome<html/>".length());
    }
    @RequestMapping("/htm")
    public void html(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println("<html>welcome<html/>");
        resp.getWriter().flush();
        resp.getWriter().close();
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
