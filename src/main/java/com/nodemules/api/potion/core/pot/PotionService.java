package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.messaging.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Jay Reed
 * @since 12/16/17
 */
@Slf4j
@Service
public class PotionService implements PotionOperations {

  private RabbitTemplate potionBrewingTemplate;

  @Autowired
  public PotionService(RabbitTemplate potionBrewingTemplate) {
    this.potionBrewingTemplate = potionBrewingTemplate;
  }

  @Override
  public void brewPotion(Potion potion) {
    log.info("Brewing a potion: {}", potion.getName());
    MessageProperties props = new MessageProperties();
    props.setExpiration("5000");
    Message message = new Message(MessageUtil.writeBody(potion), props);
    potionBrewingTemplate.send(message);
  }
}
