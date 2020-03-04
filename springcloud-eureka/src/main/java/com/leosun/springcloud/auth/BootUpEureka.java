package com.leosun.springcloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BootUpEureka {
    public static void main(String[] args) {
        SpringApplication.run(BootUpEureka.class, args);
    }
}
