package com.swap.apis.util;

public class RequestContext {
  private RequestContext() {
  }

  private static final ThreadLocal<String> apiCallerThreadLocal = new InheritableThreadLocal<>();

  public static void setCaller(String ip) {
    apiCallerThreadLocal.set(ip);
  }

  public static String getCaller() {
    return apiCallerThreadLocal.get();
  }

  public static void clear() {
    apiCallerThreadLocal.remove();
  }

}
