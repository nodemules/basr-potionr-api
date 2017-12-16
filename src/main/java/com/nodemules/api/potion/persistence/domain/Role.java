package com.nodemules.api.potion.persistence.domain;

import java.util.Collection;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
@Entity
@Table(name = "role")
public class Role {

  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private Long roleId;

  private String name;

  @OneToMany
  @JoinColumn(name = "role_id")
  private Set<User> users;

  @ManyToMany
  @JoinTable(
      name = "role_permission",
      joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "role_id"),
      inverseJoinColumns = @JoinColumn(
          name = "permission_id", referencedColumnName = "permission_id"))
  private Collection<Permission> permissions;
}
