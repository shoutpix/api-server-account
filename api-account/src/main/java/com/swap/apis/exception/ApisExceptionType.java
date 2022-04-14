package com.swap.apis.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum ApisExceptionType {
  BAD_REQUEST("BadRequest", 100),
  INTERNAL_SERVER_ERROR("Internal Server Error", 100),
  UNAUTHORIZED("Unauthorized", 200)
  ;

  private String type;
  private int value;
  
}
