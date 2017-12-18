package com.nodemules.api.potion.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author brent
 * @since 12/17/17.
 */
@Data
@Entity
@Table(name = "currency")
public class Currency {

  @Id
  @GeneratedValue
  @Column(name = "currency_id")
  private Long currencyId;

  private long amount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
