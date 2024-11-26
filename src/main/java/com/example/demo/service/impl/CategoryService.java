package com.example.demo.service.impl;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Result;

import java.util.List;

public interface CategoryService {
    //增加文章分类
    void add(Category category);
    //列表查询
    List<Category> list();


    //根据编号获取执行目录信息
    Category findById(Integer id);

    //修改目录内容
    void update(Category category);
    // 根据ID删除
    void deleteById(Integer id);
}
