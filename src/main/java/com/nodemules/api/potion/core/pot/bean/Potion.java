package com.nodemules.api.potion.core.pot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author brent
 * @since 12/14/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Potion {

  private int brewTime;
  private String name;
  private int color;
}
