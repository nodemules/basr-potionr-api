package com.nodemules.api.potion.core.auth;

/**
 * @author brent
 * @since 12/14/17.
 */
public interface AuthOperations {

  /**
   * Logs the user in
   *
   * @param username
   * @param password
   */
  void login(String username, String password);

  /**
   * Registers a new user
   *
   * @param username
   * @param password
   * @param email
   */
  void register(String username, String password, String email);
}
