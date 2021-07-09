package com.redis.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Tharindu Eranga
 * @date Fri 09 Jul 2021
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    public String host;
    @Value("${spring.redis.port}")
    public String port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        var poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(50);
        poolConfig.setMaxWaitMillis(1000);
        try (Jedis ignored = new JedisPool(poolConfig, host, Integer.parseInt(port)).getResource()) {}
        return poolConfig;
    }
}
