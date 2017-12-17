package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import java.util.List;

/**
 * @author Jay Reed
 * @since 12/16/17
 */
public interface PotionOperations {

  /**
   * Brews a potion.
   *
   */
  void brewPotion(Potion potion);

  /**
   * Gets a list of potion types.
   *
   * @return
   */
  List<PotionType> getPotionTypes();

}
