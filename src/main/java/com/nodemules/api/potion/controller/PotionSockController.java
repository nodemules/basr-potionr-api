package com.nodemules.api.potion.controller;

import com.nodemules.api.potion.core.pot.bean.Potion;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author brent
 * @since 12/20/17.
 */
@Slf4j
@Controller
@MessageMapping("/sock/potion")
public class PotionSockController {

  @MessageMapping("/name")
  @SendTo("/topic/potion")
  public Potion fromName(String name) {
    Potion pot = new Potion();
    pot.setName(name);
    pot.setBrewDate(new Date());
    pot.setFlavorText("Flavorless text");
    return pot;
  }

  @MessageMapping("/type")
  @SendTo("/topic/potion")
  public Potion fromType(String type) {
    Potion pot = new Potion();
    pot.setName("Unnamed potion");
    pot.setBrewDate(new Date());
    pot.setFlavorText(String.format("A %s type of potion", type));
    return pot;
  }

}
