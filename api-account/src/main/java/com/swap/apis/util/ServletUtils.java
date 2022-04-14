package com.swap.apis.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;

public class ServletUtils {
  public static String getClientIp(HttpServletRequest httpServletRequest) {
    if (httpServletRequest != null) {
      return httpServletRequest.getRemoteAddr();
    }
    return Strings.EMPTY;
  }
}
