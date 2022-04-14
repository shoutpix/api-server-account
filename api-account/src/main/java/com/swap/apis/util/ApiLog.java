package com.swap.apis.util;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiLog {

  private String apiUrl;
  private String args;
  private String result;
  private String clientIp;
  private String exception;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime accessTime;
  private long processingTime;
}
