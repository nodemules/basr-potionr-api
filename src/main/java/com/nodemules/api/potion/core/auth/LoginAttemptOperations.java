package com.nodemules.api.potion.core.auth;

/**
 * @author brent
 * @since 12/15/17.
 */
public interface LoginAttemptOperations {

  /**
   * Records when a user successfully logs in
   *
   * @param userId
   */
  void loginSuccess(Long userId);

  /**
   * Called when a login attempt has failed
   *
   * @param userId
   * @return
   */
  void loginFailed(Long userId);

  /**
   * Checks if the user is blacklisted from the current request's IP Address
   *
   * @param userId
   */
  void checkBlacklist(Long userId);
}
