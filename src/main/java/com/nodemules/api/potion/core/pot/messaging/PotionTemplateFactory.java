package com.nodemules.api.potion.core.pot.messaging;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author brent
 * @since 12/17/17.
 */
@Component
public class PotionTemplateFactory {

  private static final String QUEUE_POTION_BREWING = "potion.brewing";

  private ConnectionFactory connectionFactory;
  private MessageConverter jsonMessageConverter;

  @Autowired
  public PotionTemplateFactory(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
    this.connectionFactory = connectionFactory;
    this.jsonMessageConverter = jsonMessageConverter;
  }

  @Bean
  public RabbitTemplate potionBrewingTemplate() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jsonMessageConverter);
    template.setQueue(QUEUE_POTION_BREWING);
    template.setRoutingKey(QUEUE_POTION_BREWING);
    return template;
  }

}
