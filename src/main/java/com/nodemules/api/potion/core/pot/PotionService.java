package com.nodemules.api.potion.core.pot;

import com.github.javafaker.Faker;
import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import com.nodemules.api.potion.core.user.UserOperations;
import com.nodemules.api.potion.core.user.UserService;
import com.nodemules.api.potion.messaging.MessageUtil;
import com.nodemules.api.potion.persistence.repository.PotionRepository;
import com.nodemules.api.potion.persistence.repository.PotionTypeRepository;
import java.util.Date;
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

  private UserOperations userService;

  private PotionRepository potionRepository;
  private PotionTypeRepository potionTypeRepository;

  private PotionMapper potionMapper;

  private static final Faker faker = new Faker();

  @Autowired
  public PotionService(RabbitTemplate potionBrewingTemplate,
      UserService userService,
      PotionRepository potionRepository,
      PotionTypeRepository potionTypeRepository, PotionMapper potionMapper) {
    this.potionBrewingTemplate = potionBrewingTemplate;
    this.userService = userService;
    this.potionRepository = potionRepository;
    this.potionTypeRepository = potionTypeRepository;
    this.potionMapper = potionMapper;
  }

  @Override
  public void brewPotion(Potion potion) {

    potion.setBrewDate(new Date());
    potion.setBrewer(userService.getCurrentUser());

    log.info("Brewing a [{}] potion!", potion.getType().getName().toUpperCase());

    MessageProperties props = new MessageProperties();
    int expireTime = potion.getType().getBrewTime() * 1000;
    props.setExpiration(String.valueOf(expireTime));

    Message message = new Message(MessageUtil.writeBody(potion), props);

    potionBrewingTemplate.send(message);
  }

  @Override
  public void brewUniquePotion(Potion potion) {
    String a = faker.lordOfTheRings().character();
    String b = faker.rickAndMorty().location();
    String c = faker.twinPeaks().quote();

    String name = String.format("%s %s of the %s", a, b, c);

    potion.setName(name);
    potion.setFlavorText(faker.lorem().paragraph());

    potionRepository.save(potionMapper.toEntity(potion));
  }

  @Override
  public void savePotion(Potion potion) {
    potionRepository.save(potionMapper.toEntity(potion));
  }

  @Override
  public List<PotionType> getPotionTypes() {
    return potionMapper.asPotionTypeBeans(potionTypeRepository.findAll());
  }
}
