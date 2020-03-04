package com.leosun.springcloud.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.leosun.springcloud.app")
@EnableAsync
public class BootUpApp {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {

        SpringApplication.run(BootUpApp.class, args);
        logger.info("应用[{}]启动完成", BootUpApp.class.getPackage());
    }
}
