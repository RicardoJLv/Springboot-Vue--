package com.example.demo.mapper;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)"+
    "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //分页查询,这里需要使用动态SQL,使用注解写比较麻烦,使用映射配置文件,在resources/com/example/demo/mapper文件下

    List<Article> list(Integer userId, String categoryId, String state);


}
