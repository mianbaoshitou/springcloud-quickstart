package com.leosun.springcloud.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

/**
 * @program: linked-base-gateway
 * @description: Jwt工具类
 * @author: Yueling
 * @create: 2019-11-30 11:35
 **/
public class JwtUtils {

    public static Map<String, Claim> takeFromJwt(String jwt){
        return JWT.decode(jwt).getClaims();

    }




}
