package com.example.demo.mapper;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增文章类别
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)"+" values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
    //查询列表,并返回列表集合
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);
    //显示指定目录信息
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);
    //修改目录内容
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
    //删除指定目录
    @Delete("delete from category where id=#{id}")
    void deleteById(Integer id);
}
