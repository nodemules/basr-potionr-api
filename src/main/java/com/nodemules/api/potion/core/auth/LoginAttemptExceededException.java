package com.nodemules.api.potion.core.auth;

/**
 * @author brent
 * @since 12/16/17.
 */
public class LoginAttemptExceededException extends RuntimeException {

  private static final long serialVersionUID = -5392693039142952134L;

  public LoginAttemptExceededException(String message) {
    super(message);
  }
}
