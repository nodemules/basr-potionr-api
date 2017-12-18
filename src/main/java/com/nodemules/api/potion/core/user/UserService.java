package com.nodemules.api.potion.core.user;

import com.nodemules.api.potion.core.auth.UserMapper;
import com.nodemules.api.potion.core.auth.bean.User;
import com.nodemules.api.potion.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author brent
 * @since 12/17/17.
 */
@Slf4j
@Service
public class UserService implements UserOperations {

  private UserRepository userRepository;

  private UserMapper userMapper;

  @Autowired
  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public User getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    log.info("Principal is: {}", auth.getPrincipal());
    String username = (String) auth.getPrincipal();

    return userMapper.toBean(userRepository.findByUsernameOrEmail(username));
  }

}
