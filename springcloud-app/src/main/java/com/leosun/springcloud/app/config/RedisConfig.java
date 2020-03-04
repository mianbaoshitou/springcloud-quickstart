package com.leosun.springcloud.app.config;

import com.leosun.springcloud.common.utils.RedissonRepo;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    @Bean
    RedissonClient redisson()  {
        Config config = new Config();

        DynamicStringProperty REDIS_ADDRESS =
                DynamicPropertyFactory.getInstance().getStringProperty("redis.address", "redis://localhost:63791");
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.useSingleServer().setAddress(REDIS_ADDRESS.get());
        return Redisson.create(config);
    }
    @Bean
    RedissonRepo redissonRepo(RedissonClient redissonClient){
        return new RedissonRepo(redisson());
    }

}
