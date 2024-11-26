package com.example.demo.controller;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.PageBean;
import com.example.demo.pojo.Result;
import com.example.demo.service.impl.ArticleService;
import com.example.demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //标识这是一个控制器,所有方法都会返回一个响应体(JSON格式)
@RequestMapping("/article") //类中的所以方法都会响应以"/article"开头的http请求
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {  //Validated用来对类进行校验
        articleService.add(article);
        return Result.success();
    }
    @GetMapping //PageBean类用来提供返回的参数(total和items)
    public Result<PageBean<Article>> list(Integer pageNum,
                                          Integer pageSize,
                                          @RequestParam(required = false)String categoryId,
                                          @RequestParam(required = false)String state){

        PageBean<Article> pageBean= articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }
}
