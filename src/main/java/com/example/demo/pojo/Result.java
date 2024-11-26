package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data  //自动添加方法,setter..
public class Result<T>{//用来表示对前端数据的响应结果
    private Integer code;//业务状态码,0-成功,1-失败
    private String message;//提示信息
    private T data;  //响应数据
    public static <E> Result<E> success(E data){
        return new Result<>(0,"操作成功",data);
    }
    public static Result success(){
        return new Result(0,"操作成功",null);
    }
    public static Result error(String message){
        return new Result(1,message,null);
    }
}
