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
@Table(name = "login_attempt")
public class LoginAttempt {

  @Id
  @GeneratedValue
  private Long loginAttemptId;

  @IpAddress
  @Length(min = 8, max = 16)
  private String ipAddress;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  private Date attemptTime;

  private String userAgent;

}
