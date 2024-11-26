package com.example.demo.interceptor;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterseptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //令牌拦截并验证
        String token = request.getHeader("Authorization");

        try {
            Map<String, Object> result = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(result);

            return true;
        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
