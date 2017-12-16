package com.nodemules.api.potion.core.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * @author brent
 * @since 12/15/17.
 */
public final class HttpRequestUtil {

  private HttpRequestUtil() {
    throw new IllegalArgumentException("Private constructor!");
  }

  public static String getUserAgent(HttpServletRequest request) {
    return request.getHeader("User-Agent");
  }

  public static String getIpAddress(HttpServletRequest request) {
    String remoteAddr = "";

    if (request != null) {
      remoteAddr = request.getHeader("X-FORWARDED-FOR");
      if (remoteAddr == null || "".equals(remoteAddr)) {
        remoteAddr = request.getRemoteAddr();
      }
    }

    return remoteAddr;
  }
}
