package com.example.demo.controller;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Result;
import com.example.demo.service.impl.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping//如果不加url,默认为类的url
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success("添加成功");
    };
    @GetMapping//相同的url通过不同的请求方式来进行区分
    public Result<List<Category>> list(){
        List<Category> categories =new ArrayList<>();
        categories = categoryService.list();
        return Result.success(categories);
    }
    @GetMapping("/detail")
    public Result<Category> detail( Integer id) {
        //调用服务层方法
       Category c = categoryService.findById(id);
        return Result.success(c);
    }
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }
}
