package com.leosun.springcloud.gateway.service;

import com.auth0.jwt.interfaces.Claim;
import com.leosun.springcloud.common.ds.CONSTANTS;
import com.leosun.springcloud.common.utils.JwtUtils;
import com.leosun.springcloud.common.utils.RedissonRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

/**
 * @program: linked-base-gateway
 * @description: jwt校验过滤器
 * @author: Yueling
 * @create: 2019-11-30 20:22
 **/
@Component
public class JwtAuthFilter implements GatewayFilter,Ordered {

    private static final Logger logger = LogManager.getLogger();

//    @Autowired
    private static RedissonRepo redissonRepo = null;
    public JwtAuthFilter() {
        logger.error("init ==================>");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {

        if(null == redissonRepo) {
            redissonRepo = SpringContextUtil.getBean(RedissonRepo.class);
        }else{
            logger.error("无需重新获取");
        }
        //Pre Filter
        String jwtValues = Optional.ofNullable(serverWebExchange.getRequest().getHeaders())
                .map(h -> h.getFirst(CONSTANTS.JWTHEADERKEY))
                .filter(j -> j.startsWith(CONSTANTS.AUTHPREFIX))
                .orElseGet(()->"LOGOUT");
        if(CONSTANTS.LOGOUT.equals(jwtValues)){
            //TODO 修改响应信息
            serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            logger.error("未授权登录,转到登录");
            return serverWebExchange.getResponse().setComplete();
        }
        Map<String, Claim> claimMap = JwtUtils.takeFromJwt(jwtValues.substring(CONSTANTS.AUTHPREFIX.length()-1));
        String userid = null;

        if(null != claimMap.get(CONSTANTS.LOGINKEY))
        {
            userid = claimMap.get(CONSTANTS.LOGINKEY).asString();
        }
        if(StringUtils.isEmpty(userid)){
            //TODO 修改响应信息
            serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            logger.error("不包含JWT信息");
            return serverWebExchange.getResponse().setComplete();
        }


        //TODO 检查是否在黑名单用户列表
        if (null != redissonRepo.hgetString(CONSTANTS.BLACKJWTHOLDER, userid)){
            serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            logger.error("黑名单用户");
            return serverWebExchange.getResponse().setComplete();
        }
        //TODO 检查token是否与redis中存储的一致，用于用户新登录后，踢掉前一次登录
        //连接redis 换取权限信息
        String claimString = redissonRepo.hgetString(CONSTANTS.LOGINJWTHOLDER, CONSTANTS.LOGINKEY);
        if(null != claimString && (!claimString.equals(jwtValues))){
            //更新
            redissonRepo.hsetString(CONSTANTS.LOGINJWTHOLDER,CONSTANTS.LOGINKEY, jwtValues);
        }
        //将信息加入request header
        serverWebExchange.getRequest().mutate().header(CONSTANTS.LOGINKEY,userid).build();
//        claimMap.get("")
//        redissionRepo.getString();

        return gatewayFilterChain.filter(serverWebExchange);

    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
