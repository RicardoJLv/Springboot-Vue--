package com.example.demo.service.impl.impl;

import com.example.demo.mapper.CategoryMapper;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Result;
import com.example.demo.service.impl.CategoryService;
import com.example.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        //补充category中的空属性
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        //获取用户ID
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        //通过用户id查询用户创建的目录,因此需要传入用户ID
        //通过ThreadLocal获取
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Category> categories = categoryMapper.list(userId);
        return categories;
    }

    @Override
    public Category findById(Integer id) {
        Category c=categoryMapper.findById(id);
        return c;
    }

    @Override
    public void update(Category category) {
        //完善一些属性
        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }


}
