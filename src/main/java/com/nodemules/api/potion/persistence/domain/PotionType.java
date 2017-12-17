package com.nodemules.api.potion.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author brent
 * @since 12/17/17.
 */
@Data
@Entity
@Table(name = "potion_type")
public class PotionType {

  @Id
  @GeneratedValue
  @Column(name = "potion_type_id")
  private Long potionTypeId;

  private String name;
  private int color;
  private int brewTime;

}
