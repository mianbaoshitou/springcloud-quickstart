package com.leosun.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.leosun.springcloud.gateway")
public class BootUpGateway {
    public static void main(String[] args) {
        SpringApplication.run(BootUpGateway.class, args);
        System.out.println(BootUpGateway.class.getPackage() + BootUpGateway.class.getName() + "启动完成");
    }
}
