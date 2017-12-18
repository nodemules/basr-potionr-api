package com.nodemules.api.potion.core.pot.bean;

import com.nodemules.api.potion.core.auth.bean.User;
import java.util.Date;
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

  private Long id;
  private String name;
  private PotionType type;
  private String flavorText;
  private Date brewDate;

  private User brewer;

  public Potion(PotionType type) {
    this.type = type;
  }
}
