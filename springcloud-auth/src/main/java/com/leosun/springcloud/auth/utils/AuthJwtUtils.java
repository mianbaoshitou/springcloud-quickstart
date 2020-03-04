package com.leosun.springcloud.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.leosun.springcloud.auth.conf.Constants;
import com.leosun.springcloud.common.utils.Snowflake;
import com.leosun.springcloud.common.utils.SnowflakeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * @program: linked-base-gateway
 * @description: Jwt工具类
 * @author: Yueling
 * @create: 2019-11-30 11:35
 **/
@Service
public class AuthJwtUtils {

    public Map<String, Claim> takeFromJwt(String jwt){
        return JWT.decode(jwt).getClaims();

    }
    static final Algorithm hmac256 = Algorithm.HMAC256(Constants.SECRET);
    public String sign(String user){
        long second = Instant.EPOCH.getEpochSecond();
        return sign(Constants.ISSUER, user);

    }
    public String sign(String issuer, String user){
        long second = Instant.EPOCH.getEpochSecond();
        String token = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + (120 * 60 * 1000)))
                .withClaim("userid", user)
                .withJWTId(SnowflakeGenerator.nextId()+"")
                .sign(hmac256);
        return token;
    }



}
