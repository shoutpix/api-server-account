package com.swap.apis.model.operation;

import lombok.Data;

@Data
public class RedisInfo {
  private int seq;
  private String typeName;
  private Byte dbIndex;
  private String password;
  private boolean enable;
  
 }

