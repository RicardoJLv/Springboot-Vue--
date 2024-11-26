package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;
@Data //lombok提供,在实体类上添加setter,getter和tostring
public class User {
    @NonNull
    private Integer id; //主键ID
    private String username;
    @JsonIgnore //当User类需要被转化为Json对象时,会自动忽略password字段
    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;
    @NotEmpty
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;//下面两个字段跟数据库中的字段不一致,因此在数据库中查询不到,需要在application.yml中进行配置
    private LocalDateTime updateTime;
}
