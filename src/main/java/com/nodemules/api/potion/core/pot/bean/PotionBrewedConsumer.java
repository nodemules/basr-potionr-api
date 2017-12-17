package com.nodemules.api.potion.core.pot.bean;

import com.nodemules.api.potion.messaging.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author brent
 * @since 12/16/17.
 */
@Slf4j
@Component
public class PotionBrewedConsumer implements MessageListener {

  @Override
  public void onMessage(Message message) {
    Potion potion = MessageUtil.parse(message, Potion.class);
    log.info("A potion was brewed: {}", potion);
  }
}
