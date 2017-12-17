package com.nodemules.api.potion.messaging.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brent
 * @since 12/2/17.
 */
@Slf4j
@Configuration
public class RabbitConfiguration {

  private RabbitProperties rabbitProperties;

  @Autowired
  public RabbitConfiguration(RabbitProperties rabbitProperties) {
    this.rabbitProperties = rabbitProperties;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    log.info("Connecting to rabbit: {}@{}", rabbitProperties.getUsername(),
        rabbitProperties.getHost());
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
        rabbitProperties.getHost());
    connectionFactory.setUsername(rabbitProperties.getUsername());
    connectionFactory.setPassword(rabbitProperties.getPassword());
    return connectionFactory;
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
