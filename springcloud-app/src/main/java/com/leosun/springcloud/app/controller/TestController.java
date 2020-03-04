package com.leosun.springcloud.app.controller;

import com.leosun.springcloud.app.repository.UserTblEntity;
import com.leosun.springcloud.app.service.AsyncTestService;
import com.leosun.springcloud.common.ds.CONSTANTS;
import com.leosun.springcloud.common.ds.TronDataResult;
import com.leosun.springcloud.common.ds.TronDataResultCode;
import com.leosun.springcloud.common.utils.RedissonRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class TestController {
    @Autowired
    AsyncTestService asyncTestService;
    @Autowired
    RedissonRepo redissonRepo;

    private static final Logger logger = LogManager.getLogger();
    @GetMapping("/test2")
    public TronDataResult test(HttpServletRequest request) {
        logger.info("start {}", LocalDateTime.now());


        TronDataResult tronDataResult = new TronDataResult(TronDataResultCode.SUCCESS);

        //异步执行耗时任务1
        CompletableFuture<String> t = asyncTestService.longTask();
        //异步执行耗时任务2
        CompletableFuture<String> t2 = asyncTestService.longTask2();
//        //异步执行耗时任务3
        CompletableFuture<UserTblEntity> t3 = asyncTestService.longTask3();
        String userid = request.getHeader(CONSTANTS.LOGINKEY);
        //获取权限信息 FOR TEST
        if (!StringUtils.isEmpty(userid)) {
            String login = redissonRepo.hgetString(CONSTANTS.LOGINJWTHOLDER, userid);
            logger.info("获取权限信息{}", login);

        }
            try {
//            tronDataResult.fillDat(t.get()+t2.get()+t3.get().getUsername() + login);
                tronDataResult.fillDat(t.get() + t2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            logger.info("end controller");
            return tronDataResult;
        }
}
