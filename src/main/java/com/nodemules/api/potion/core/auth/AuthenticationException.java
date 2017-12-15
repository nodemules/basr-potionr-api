package com.nodemules.api.potion.core.auth;

/**
 * @author brent
 * @since 12/14/17.
 */
public class AuthenticationException extends RuntimeException {

  private static final long serialVersionUID = 7210306399160472863L;

  public AuthenticationException(String message) {
    super(message);
  }
}
