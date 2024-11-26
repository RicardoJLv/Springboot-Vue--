package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper {

    //在@select注解中编写sql语句
    @Select("select * from user where username =#{username}")
    User findByUserName(String username);
    @Insert("insert into user(username,password,create_time,update_time)"+" values(#{username},#{password},now(),now())")
    void add( String username,  String password);
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
    @Update("update user set user_pic=#{avatarUrl},update_time=now()  where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);
    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(String newPwd, Integer id);
}
