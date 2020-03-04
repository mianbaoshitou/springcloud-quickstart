package com.leosun.springcloud.auth.controller;

import com.leosun.springcloud.auth.repository.UserTblEntity;
import com.leosun.springcloud.auth.repository.UserTblEntityRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @program: linked-base-gateway
 * @description: 测试使用
 * @author: Yueling
 * @create: 2019-11-30 22:13
 **/
@RestController
public class TestController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    UserTblEntityRepo userTblEntityRepo;
    @GetMapping("/abc")
    public String test(){
        logger.info("i'm here");
        Optional<UserTblEntity> userTblEntity = userTblEntityRepo.findById(1L);
        return "got you !" + userTblEntity.get().getUsername();
    }
}
