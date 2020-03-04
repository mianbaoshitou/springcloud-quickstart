package com.leosun.springcloud.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: linked-base-gateway
 * @description: 启动类
 * @author: Yueling
 * @create: 2019-11-27 20:04
 **/
@SpringBootApplication
@ComponentScan("com.leosun.springcloud.auth")
public class BootUpAuth {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        logger.error("Start Auth");
        SpringApplication.run(BootUpAuth.class, args);

    }
}
