package com.nodemules.api.potion.core.pot.bean;

import lombok.Data;

/**
 * @author brent
 * @since 12/17/17.
 */
@Data
public class PotionType {

  private Long id;
  private String name;
  private int color;
  private int brewTime;
}
