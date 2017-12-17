package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.PotionBrewedConsumer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author brent
 * @since 12/17/17.
 */
@Component
public class PotionQueueFactory {

  private static final String QUEUE_POTION_BREWING = "potion.brewing";
  private static final String QUEUE_POTION_BREWED = "potion.brewed";

  private ConnectionFactory connectionFactory;
  private MessageConverter jsonMessageConverter;

  @Autowired
  public PotionQueueFactory(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
    this.connectionFactory = connectionFactory;
    this.jsonMessageConverter = jsonMessageConverter;
  }

  @Bean
  public Queue potionBrewing() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-dead-letter-exchange", "");
    args.put("x-dead-letter-routing-key", QUEUE_POTION_BREWED);
    args.put("x-message-ttl", 60000);
    return new Queue(QUEUE_POTION_BREWING, false, false, false, args);
  }

  @Bean
  public RabbitTemplate potionBrewingTemplate() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jsonMessageConverter);
    template.setQueue(QUEUE_POTION_BREWING);
    template.setRoutingKey(QUEUE_POTION_BREWING);
    return template;
  }

  @Bean
  public Queue potionBrewed() {
    return new Queue(QUEUE_POTION_BREWED);
  }

  @Bean
  public SimpleMessageListenerContainer potionBrewedListenerContainer() {
    SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
    listenerContainer.setConnectionFactory(connectionFactory);
    listenerContainer.setQueues(potionBrewed());
    listenerContainer.setMessageConverter(jsonMessageConverter);
    listenerContainer.setMessageListener(new PotionBrewedConsumer());
    listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
    return listenerContainer;
  }
}
