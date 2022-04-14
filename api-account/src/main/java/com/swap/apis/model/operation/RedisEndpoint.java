package com.swap.apis.model.operation;

import lombok.Data;

@Data
public class RedisEndpoint {
  private int seq;
  private int redisInfoSeq;
  private String host;
  private Short port;
 }

