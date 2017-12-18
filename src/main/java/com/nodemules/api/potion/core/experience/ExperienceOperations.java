package com.nodemules.api.potion.core.experience;

import com.nodemules.api.potion.core.auth.bean.User;
import com.nodemules.api.potion.core.pot.bean.PotionType;

/**
 * @author brent
 * @since 12/17/17.
 */
public interface ExperienceOperations {

  /**
   * Grants an amount of experience to the user for a potion brewed
   */
  void grantUserExperienceForPotionBrewed(User user, PotionType type, boolean isUnique);
}
