package com.nodemules.api.potion.core.auth.bean;

import java.util.List;
import lombok.Data;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
public class Role {

  private Long roleId;
  private List<Permission> permissions;
}
