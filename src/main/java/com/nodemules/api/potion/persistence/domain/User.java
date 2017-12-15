package com.nodemules.api.potion.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private String email;
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;

}
