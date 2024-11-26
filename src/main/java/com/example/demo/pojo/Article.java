package com.example.demo.pojo;
import java.time.LocalDateTime;

import com.example.demo.anno.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.groups.Default;
@Data
public class Article {
    @NotNull(groups = Update.class)  //在更新文章时会用到
    private Integer id; //主键ID

    @NotEmpty //字符串一般使用NotEmpty进行校验
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    @URL
    private String coverImg;
    @State
    private String state; //发布状态 已发布|草稿
    @NotNull
    private Integer categoryId;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    //添加分组
    public interface Add extends Default {};
    public interface Update extends Default {};
}
