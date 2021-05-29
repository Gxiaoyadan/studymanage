package com.example.studymanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfig {

    /**
     * @Description:@Bean,将方法返回的实例注入到IOC中（jar包中的类，不会被扫描，注入jar包中方法时会报错）
     * @Author: lgzhang
     * @Date:   2021/5/26 19:53
     * @Param:  []
     * @Return: redis.clients.jedis.Jedis
     */
    @Bean
    Jedis getJedis(){
        Jedis jedis = new Jedis();
        return jedis;
    }
}
