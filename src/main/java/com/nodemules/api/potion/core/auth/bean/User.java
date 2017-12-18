package com.nodemules.api.potion.core.auth.bean;

import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
public class User {

  private Long id;
  private String username;
  private String email;

  @Transient
  private String password;
  private Role role;

  private int level = 1;

}
