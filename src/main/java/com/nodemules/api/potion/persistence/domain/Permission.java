package com.nodemules.api.potion.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
@Entity
@Table(name = "permission")
public class Permission {

  @Id
  @GeneratedValue
  @Column(name = "permission_id")
  private Long permissionId;
  private String name;

}
