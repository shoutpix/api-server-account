package com.swap.apis.mapper.operation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.swap.apis.model.operation.RedisEndpoint;
import com.swap.apis.model.operation.RedisInfo;

@Mapper
public interface RedisMapper {

  List<RedisInfo> selectRedisInfoList();
  
  List<RedisEndpoint> selectRedisEndpointsList();
}
