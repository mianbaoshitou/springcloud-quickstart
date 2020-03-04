package com.leosun.springcloud.auth.conf;


import com.leosun.springcloud.common.utils.Snowflake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {

    @Bean
    public Snowflake snowflake(){
        return new Snowflake(0,1);
    }
}
