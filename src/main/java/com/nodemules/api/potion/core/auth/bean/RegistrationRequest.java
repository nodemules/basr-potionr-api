package com.nodemules.api.potion.core.auth.bean;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
public class RegistrationRequest implements Serializable {

  private static final long serialVersionUID = 3645002746411158846L;

  @NotNull
  private String username;
  @NotNull
  private String email;
  @NotNull
  private String password;
}
