package com.example.demo.service.impl;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.PageBean;


public interface ArticleService {
    //新增文章
    void add(Article article);
    //条件分页查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);
}
