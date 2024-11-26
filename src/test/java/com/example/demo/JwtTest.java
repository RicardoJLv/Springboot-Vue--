package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test //这个注解用来告诉测试运行器，标注的方法是一个测试用例。当运行测试时，测试框架会查找所有被 @Test 注解的方法并执行它们。
    public void testGenerateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "jwj");
        String token=JWT.create()
                        .withClaim("user",claims)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*24))//添加令牌到期时间
                        .sign(Algorithm.HMAC256("secret")); //指定加密的算法并配置密钥
        System.out.println(token);
    }

    //编写一个代码用来解码token
    @Test
    public void testDecodeToken() {

        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoiandqIn0sImV4cCI6MTczMjE3NzYzNn0" +
                ".of8BB5kITOoGlM7yT2MH6eOf_HdjMpuUX5nn1guP78c";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("secret")).build(); //生成解析器并确定算法和密钥
        DecodedJWT decodedJWT = jwtVerifier.verify(token); //验证token并生成解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
