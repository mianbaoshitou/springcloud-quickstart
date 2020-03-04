package com.leosun.springcloud.auth.conf;

import com.leosun.springcloud.common.utils.RedissonRepo;
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
        //config.useClusterServers().addNodeAddress("127.0.0.1:6379");
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
    @Bean
    RedissonRepo redissonRepo(RedissonClient redissonClient){
        return new RedissonRepo(redisson());
    }

}
