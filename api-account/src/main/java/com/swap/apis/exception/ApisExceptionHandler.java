package com.swap.apis.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApisExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ApisException.class)
  @ResponseBody
  public ResponseEntity<Map<String, String>> handleApisException(ApisException apisException) {
    logger.debug("> apis exception");
    return ResponseEntity.ok(Map.of("result", "fail", "message", apisException.getMessage()));
  }

  @ExceptionHandler(JeffException.class)
  @ResponseBody
  public ResponseEntity<Map<String, String>> handleJeffException(JeffException jeffException) {
    logger.debug("> jeff exception");
    return ResponseEntity.ok(Map.of("result", "fail", "message", jeffException.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<Object> handleApisException(Exception e) {
	logger.error("unexpected exception", e);
    return ResponseEntity.ok(Map.of("result", "fail", "message", "internal server exception"));
  }
}
