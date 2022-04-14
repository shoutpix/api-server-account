package com.swap.apis.util;

public class KeyUtils {

  public static long calculateInitialNumberKeyValue(int minLength) {
    if ( minLength < 2) {
      return 0;
    }
    return (long) Math.pow(10, minLength - 1);
  }

}
