package com.example.demo.mapper;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
    "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //分页查询
    @Select("select ")
    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);
}
