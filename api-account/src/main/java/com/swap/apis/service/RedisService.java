package com.swap.apis.service;

import java.util.List;

import com.swap.apis.model.operation.RedisEndpoint;
import com.swap.apis.model.operation.RedisInfo;

public interface RedisService {
	
	void getRedisStringValue(String key);
 
	List<RedisInfo> selectRedisInfoList();
  
    List<RedisEndpoint> selectRedisEndpointsList();
    
}
