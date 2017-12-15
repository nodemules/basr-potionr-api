package com.nodemules.api.potion.controller;

import com.nodemules.api.potion.core.auth.AuthOperations;
import com.nodemules.api.potion.core.auth.AuthService;
import com.nodemules.api.potion.core.auth.bean.RegistrationRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
  public void login(@RequestHeader String username, @RequestHeader String password) {
    authService.login(username, password);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public void register(@RequestBody @Valid RegistrationRequest registrationRequest) {
    authService.register(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail());
  }
}
