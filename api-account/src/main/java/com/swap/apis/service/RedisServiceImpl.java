package com.swap.apis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.swap.apis.mapper.operation.RedisMapper;
import com.swap.apis.model.operation.RedisEndpoint;
import com.swap.apis.model.operation.RedisInfo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	private final RedisMapper redisMapper;

	public RedisServiceImpl(RedisMapper redisMapper) {
		this.redisMapper = redisMapper;
	}

	@Override
	public List<RedisInfo> selectRedisInfoList() {
		List<RedisInfo> redisInfos = redisMapper.selectRedisInfoList();
		return redisInfos;
	}

	@Override
	public List<RedisEndpoint> selectRedisEndpointsList() {
		List<RedisEndpoint> redisEndpointsList = redisMapper.selectRedisEndpointsList();
		return redisEndpointsList;
	}

	@Override
	public void getRedisStringValue(String key) {
		ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
		System.out.println("Redis key : " + key);
		System.out.println("Redis value : " + stringValueOperations.get(key));
	}

}
