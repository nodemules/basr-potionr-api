package com.nodemules.api.potion.core.pot.messaging;

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

  @Autowired
  public PotionBrewedConsumer(PotionService potionService) {
    this.potionService = potionService;
  }

  @Override
  public void onMessage(Message message) {
    Potion potion = MessageUtil.parse(message, Potion.class);
    log.info("A potion was brewed: {}", potion);
    // potionService.generateExperience(potion);
    // potionService.generateCurrency(potion);
    if (random.nextBoolean()) {
      log.info("Brewing a unique potion!");
      potionService.brewUniquePotion(potion);
      // potionService.generateExperience(potion);
      // potionService.generateCurrency(potion);
    }
    // send message about experience generated
    // send message about currency generated
    // send message about unique potion generated

  }
}
