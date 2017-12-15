package com.nodemules.api.potion.core.auth;

import com.nodemules.api.potion.core.auth.bean.User;
import com.nodemules.api.potion.peristence.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author brent
 * @since 12/14/17.
 */
@Slf4j
@Service
public class AuthService implements AuthOperations {

  private UserRepository userRepository;

  private UserMapper userMapper;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public AuthService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void login(String login, String password) {
    User user = userMapper.toBean(userRepository.findByUsernameOrEmail(login));

    if (user == null) {
      throw new AuthenticationException("Unable to login user: " + login);
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new AuthenticationException("Unable to authenticate");
    }

    setAuthentication(user);
  }

  private void setAuthentication(User user) {
    List<? extends GrantedAuthority> permissions = new ArrayList<>();
    if (user.getRole() != null && !CollectionUtils.isEmpty(user.getRole().getPermissions())) {
      permissions = user.getRole().getPermissions();
    }

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, permissions);
    SecurityContextHolder.getContext().setAuthentication(auth);

  }

  @Override
  public void register(String username, String password, String email) {
    checkIfUserExists(username, email);

    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);

    userRepository.save(userMapper.toEntity(user));

    setAuthentication(user);
  }

  private void checkIfUserExists(@NotNull String username, @NotNull String email) {
    User user = userMapper.toBean(userRepository.findByUsernameOrEmail(username, email));

    if (user != null) {
      if (email.equals(user.getEmail())) {
        throw new RegistrationException("E-mail is taken");
      }
      if (username.equals(user.getUsername())) {
        throw new RegistrationException("Username is taken");
      }
      throw new RegistrationException("Something bad happened");
    }
  }
}
