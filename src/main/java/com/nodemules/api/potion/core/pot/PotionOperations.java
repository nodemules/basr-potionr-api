package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.Potion;

/**
 * @author Jay Reed
 * @since 12/16/17
 */
public interface PotionOperations {

  /**
   * Brews a potion.
   */
  void brewPotion(Potion potion);
}
