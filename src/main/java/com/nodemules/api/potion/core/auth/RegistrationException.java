package com.nodemules.api.potion.core.auth;

/**
 * @author brent
 * @since 12/14/17.
 */
public class RegistrationException extends RuntimeException {

  private static final long serialVersionUID = -245938727282006250L;

  public RegistrationException(String message) {
    super(message);
  }
}
