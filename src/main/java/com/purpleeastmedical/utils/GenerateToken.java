package com.purpleeastmedical.utils;

import com.auth0.jwt.algorithms.Algorithm;
import com.purpleeastmedical.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author loongzhang
 * @Description 使用JWT生成相关信息验证
 * @date 2023-01-11-9:38
 */
@Slf4j
public class GenerateToken {
    public static String generateToken(UserDTO user){
        // 设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,60*60);
        // 生成token
        String token = com.auth0.jwt.JWT.create()
                // 头部信息
                .withHeader(new HashMap<>())
                // 以下withClaim均为PayLoad 一般该位置用来存放用户信息
                .withClaim("userName", user.getUserName())
                .withClaim("mobile", user.getMobile())
                .withClaim("email", user.getEmail())
                .withClaim("gender", user.getGender())
                // 设置过期时间
                .withExpiresAt(calendar.getTime())
                // 签名用的加密方法
                .sign(Algorithm.HMAC256("!34ADAS"));
        log.info("生成的token{}",token);
        return token;
    }

}
