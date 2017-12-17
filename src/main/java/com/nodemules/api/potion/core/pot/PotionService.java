package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.Potion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Jay Reed
 * @since 12/16/17
 */
@Service
public class PotionService implements PotionOperations {

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public PotionService(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;

  }

  @Override
  public void brewPotion(Potion potion) {
    rabbitTemplate.convertAndSend(potion);
  }
}
