package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import com.nodemules.api.potion.messaging.MessageUtil;
import com.nodemules.api.potion.persistence.repository.PotionTypeRepository;
import java.util.List;
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

  private PotionTypeRepository potionTypeRepository;

  private PotionMapper potionMapper;

  @Autowired
  public PotionService(RabbitTemplate potionBrewingTemplate,
      PotionTypeRepository potionTypeRepository, PotionMapper potionMapper) {
    this.potionBrewingTemplate = potionBrewingTemplate;
    this.potionTypeRepository = potionTypeRepository;
    this.potionMapper = potionMapper;
  }

  @Override
  public void brewPotion(Potion potion) {
    log.info("Brewing a potion: {}", potion.getName());
    MessageProperties props = new MessageProperties();
    int expireTime = potion.getBrewTime() * 1000;
    props.setExpiration(String.valueOf(expireTime));
    Message message = new Message(MessageUtil.writeBody(potion), props);
    potionBrewingTemplate.send(message);
  }

  @Override
  public List<PotionType> getPotionTypes() {
    return potionMapper.asPotionTypeBeans(potionTypeRepository.findAll());
  }
}
