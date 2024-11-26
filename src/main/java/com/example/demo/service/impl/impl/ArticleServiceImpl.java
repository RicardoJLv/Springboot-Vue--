package com.example.demo.service.impl.impl;

import com.example.demo.mapper.ArticleMapper;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.PageBean;
import com.example.demo.service.impl.ArticleService;
import com.example.demo.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service //在实现类中添加注解
public class ArticleServiceImpl implements ArticleService {
    //在Service中使用Mapper对象,要自动注入
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //在传递的数据中,没有创建者的ID,创建时间,更新时间,需要添加
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        //调用mapper方法
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> pb= new PageBean<>();
        //开启分页查询PageHelper
        PageHelper.startPage(pageNum,pageSize); //传两个参数
        //调用mapper对象
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);//PageHelper返回一个List
        //Page中提供了方法,可以获得PageHelper分类查询后得到的总记录条数和当前页数据,Page相当于Pagehelper的实现类
        Page<Article> p =(Page<Article>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
