package com.nodemules.api.potion.controller;

import com.nodemules.api.potion.core.auth.AuthOperations;
import com.nodemules.api.potion.core.auth.AuthService;
import com.nodemules.api.potion.core.auth.bean.RegistrationRequest;
import com.nodemules.api.potion.security.SecurityUtil;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brent
 * @since 12/14/17.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

  private AuthOperations authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void login(@RequestHeader(value = "Authorization") String authorization) {
    Pair<String, String> credentials = SecurityUtil.decodeAuthorizationKey(authorization);
    authService.login(credentials.getKey(), credentials.getValue());
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public void register(@RequestBody @Valid RegistrationRequest registrationRequest) {
    authService.register(registrationRequest.getUsername(), registrationRequest.getPassword(),
        registrationRequest.getEmail());
  }
}
