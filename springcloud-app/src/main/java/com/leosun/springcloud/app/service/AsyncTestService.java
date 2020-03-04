package com.leosun.springcloud.app.service;

import com.leosun.springcloud.app.repository.UserTblEntity;
import com.leosun.springcloud.app.repository.UserTblEntityRepo;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;

@Async("ayncFutureExecutor")
@Service
public class AsyncTestService {
    private static Logger logger = LogManager.getLogger();
    @Autowired
    UserTblEntityRepo userTblEntityRepo;

    public CompletableFuture<String> longTask() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        logger.info("当前线程{}", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Finish");
    }
    public CompletableFuture<String> longTask2() {
        logger.info("当前线程{}", Thread.currentThread().getName());
        CompletableFuture<String> cf = new CompletableFuture<>();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Finish2");
    }

    public CompletableFuture<UserTblEntity> longTask3(){
        CompletableFuture<String> cf = new CompletableFuture<>();
        logger.info("当前线程{}", Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
            DynamicStringProperty TEST_ARCHAIUS =
                    DynamicPropertyFactory.getInstance().getStringProperty("test_archaius", "hello,world");
            logger.info("时间{},test_archaius2 属性值{}", now(), TEST_ARCHAIUS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture( userTblEntityRepo.findById(1L).get());
    }
}
