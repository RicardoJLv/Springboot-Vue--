//这个包用来提供自定义注解,因为现有注解无法满足我们参数校验的要求
package com.example.demo.anno;

import com.example.demo.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//需要添加下面的注解
@Documented  //元注解
@Target({FIELD})//元注解,表示自定义注解使用的场景,比如方法,参数还是类;
@Retention(RUNTIME) //元注解,表示自定义注解保留的阶段,比如编译阶段,运行阶段
@Constraint(validatedBy = {StateValidation.class}) //指定提供校验规则的类,这个类不继承我们提供的接口
public @interface State {

    //模仿其他注解来编写
    //message提供校验失败后的提示信息
    String message() default "{state的参数只能为草稿或者已发布}";
    //指定分组
    Class<?>[] groups() default {};
    //负载,获取到State注解提供的附加信息
    Class<? extends Payload>[] payload() default {};

}
