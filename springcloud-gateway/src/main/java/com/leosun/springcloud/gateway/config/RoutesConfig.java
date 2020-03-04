package com.leosun.springcloud.gateway.config;

import com.leosun.springcloud.gateway.service.JwtAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: linked-base-gateway
 * @description: 自定义route
 * @author: Yueling
 * @create: 2019-11-30 21:39
 **/
@Configuration
public class RoutesConfig {
    @Bean
    public RouteLocator chRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(
                    r -> r.path("/app/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(new JwtAuthFilter()))
                        .uri("http://localhost:8800")
                        .order(0)
                        .id("jwt")
                )
                .route(
                        loginRoute -> loginRoute.path("/login")
                            .uri("http://localhost:8801")
                        .order(Integer.MAX_VALUE)
                        .id("login")
                )
                .build();
    }
}
