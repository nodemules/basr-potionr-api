package com.nodemules.api.potion.persistence.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * @author brent
 * @since 12/17/17.
 */
@Data
@Entity
@Table(name = "potion")
public class Potion {

  @Id
  @GeneratedValue
  @Column(name = "potion_id")
  private Long potionId;

  @ManyToOne
  @JoinColumn(name = "potion_type_id")
  private PotionType potionType;

  private String name;

  private String flavorText;

  @Temporal(TemporalType.TIMESTAMP)
  private Date brewDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User brewer;

}
