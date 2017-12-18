package com.nodemules.api.potion.core.pot.messaging;

import com.nodemules.api.potion.core.currency.CurrencyOperations;
import com.nodemules.api.potion.core.currency.CurrencyService;
import com.nodemules.api.potion.core.experience.ExperienceOperations;
import com.nodemules.api.potion.core.experience.ExperienceService;
import com.nodemules.api.potion.core.pot.PotionOperations;
import com.nodemules.api.potion.core.pot.PotionService;
import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.messaging.MessageUtil;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author brent
 * @since 12/16/17.
 */
@Slf4j
@Component
public class PotionBrewedConsumer implements MessageListener {

  private static final Random random = new Random();

  private PotionOperations potionService;
  private ExperienceOperations experienceService;
  private CurrencyOperations currencyService;

  @Autowired
  public PotionBrewedConsumer(PotionService potionService, ExperienceService experienceService,
      CurrencyService currencyService) {
    this.potionService = potionService;
    this.experienceService = experienceService;
    this.currencyService = currencyService;
  }

  @Override
  public void onMessage(Message message) {
    Potion potion = MessageUtil.parse(message, Potion.class);
    boolean unique = false;
    log.info("A potion was brewed: {}", potion);
    if (random.nextBoolean()) {
      unique = true;
      log.info("Brewing a unique potion!");
      potionService.brewUniquePotion(potion);
    }
    currencyService.grantUserCurrencyForPotionBrewed(potion.getBrewer(), potion.getType(), unique);
    experienceService
        .grantUserExperienceForPotionBrewed(potion.getBrewer(), potion.getType(), unique);

  }
}
