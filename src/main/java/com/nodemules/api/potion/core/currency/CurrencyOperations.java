package com.nodemules.api.potion.core.currency;

import com.nodemules.api.potion.core.auth.bean.User;
import com.nodemules.api.potion.core.pot.bean.PotionType;

/**
 * @author brent
 * @since 12/17/17.
 */
public interface CurrencyOperations {

  /**
   * Grants an amount of currency to a user for a Potion brewed
   */
  void grantUserCurrencyForPotionBrewed(User user, PotionType type, boolean isUnique);
}
