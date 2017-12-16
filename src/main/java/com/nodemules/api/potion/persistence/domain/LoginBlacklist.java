package com.nodemules.api.potion.persistence.domain;

import com.nodemules.api.potion.core.auth.IpAddress;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author brent
 * @since 12/15/17.
 */
@Data
@Entity
@Table(name = "login_blacklist")
public class LoginBlacklist {

  @Id
  @GeneratedValue
  private Long loginBlacklistId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @IpAddress
  @Length(min = 8, max = 16)
  private String ipAddress;

  @Temporal(TemporalType.TIMESTAMP)
  private Date attemptTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Date expireTime;

  private String userAgent;

}
