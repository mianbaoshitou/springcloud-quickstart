package com.leosun.springcloud.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * @program: linked-base-gateway
 * @description: 授权
 * @author: Yueling
 * @create: 2019-11-30 21:06
 **/
@Async
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Autowired
    JwtAuthFilter jwtAuthFilter;
    @Override
    public GatewayFilter apply(Object o) {
        return jwtAuthFilter;
    }
}
