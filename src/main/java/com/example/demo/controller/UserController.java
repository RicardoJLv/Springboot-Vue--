package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp ="^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User user = userService.findByUserName(username);
        if (user==null){
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户名已被注册");
        }
    }
    @PostMapping("/login")
    public  Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User loginuser = userService.findByUserName(username);
        if (loginuser==null){
            return Result.error("用户名错误");
        }

        if (password.equals(loginuser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginuser.getId());
            claims.put("username",loginuser.getUsername());
            String token = JwtUtil.generateToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
    //下面这个代码也是可以实现获取UserInfo,但是比较麻烦,使用下面的改进的ThreadLocal方法
//    @GetMapping("/userinfo")
//    public Result<User> userinfo(@RequestHeader(name = "Authorization") String token){
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
//        User user = userService.findByUserName(username);
//        return Result.success(user);
//    }
    @GetMapping("/userinfo")
    public Result<User> userinfo(){
        Map<String,Object> map =ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
    @PutMapping("/update") //put请求一般用来更新参数
    public Result update(@RequestBody @Validated User user){ //@RequestBody将请求体中的数据转换为User实例
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL  String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    //更新密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //进行参数校验
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        //判断请求体是否传输了数据
        if (!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        //验证原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        User user = userService.findByUserName((String) map.get("username"));
        if (!user.getPassword().equals(oldPwd)){
            return Result.error("原密码填写错误");
        }
        //验证newPwd跟rePwd是否一样
        if (!newPwd.equals(rePwd)){
            return Result.error("两次填写的密码不一致");
        }
        //调用service完成密码处理
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
