package com.nodemules.api.potion.core.auth.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author brent
 * @since 12/14/17.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements GrantedAuthority {

  private Long id;
  private String authority;
}
