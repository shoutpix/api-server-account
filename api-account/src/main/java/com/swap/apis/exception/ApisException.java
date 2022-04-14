package com.swap.apis.exception;

import lombok.Data;

@Data
public class ApisException extends RuntimeException {

  private final ApisExceptionType ApisExceptionType;
  public ApisException(ApisExceptionType ApisExceptionType, String message) {
    super(message);
    this.ApisExceptionType = ApisExceptionType;
  }

  @Override
  public String toString() {
    return "ApisException{" + "exceptionType=" + ApisExceptionType +", message='" + getMessage() + '\'' + '}';
  }
}