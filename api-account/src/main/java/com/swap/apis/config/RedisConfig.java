package com.swap.apis.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.swap.apis.model.operation.RedisEndpoint;
import com.swap.apis.service.RedisService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
public class RedisConfig {
	
	private final RedisService redisService;
	private String host ;
	private Short port ;
	
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
    	List<RedisEndpoint> redisEndpointInfos = redisService.selectRedisEndpointsList();
    	for(RedisEndpoint redisEndpoint : redisEndpointInfos) {
    		if(redisEndpoint.getSeq() == 1) { 
    			host = redisEndpoint.getHost();
    			port = redisEndpoint.getPort();
    		}
    	}
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}