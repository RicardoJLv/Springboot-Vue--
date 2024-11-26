package com.example.demo.validation;

import com.example.demo.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> { //接口有两个泛型,第一个泛型表示给哪个注解提供校验规则,第二个表示将来校验的数据的数据类型
    /**
     * 
     * @param s 将来要校验的参数
     * @param constraintValidatorContext
     * @return 如果返回fales,校验不通过;返回true,校验通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null) {
            return false;
        }
        if (s.equals("草稿")||s.equals("已发布")){
            return true;
        }
        return false;


    }
}
