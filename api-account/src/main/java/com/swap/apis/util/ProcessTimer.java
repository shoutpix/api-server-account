package com.swap.apis.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.Getter;

public class ProcessTimer {
  @Getter
  private LocalDateTime accessTime;
  private LocalDateTime resultTime;
  private Duration processingTime;

  public ProcessTimer() {
    accessTime = LocalDateTime.now();
  }

  public void end() {
    resultTime = LocalDateTime.now();
    processingTime = Duration.between(resultTime, accessTime);
  }

  public long getProcessTimeNano() {
    return processingTime.get(ChronoUnit.NANOS);
  }
}
