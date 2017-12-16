package com.nodemules.api.potion.core.auth.model;

import com.nodemules.api.potion.core.auth.IpAddress;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author brent
 * @since 12/15/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAttempt {

  private Long userId;

  @IpAddress
  private String ipAddress;
  private Date attemptTime;
  private String userAgent;

}
