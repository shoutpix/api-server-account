package com.swap.apis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();
  public static String writeString(Object obj) {
	  try {
		  return objectMapper.writeValueAsString(obj);
	  } catch (JsonProcessingException e) {
		 
    	 //logger.warn("failed to write json string, ({})", obj,  e);
    	 return null;
	  }
  }
}
