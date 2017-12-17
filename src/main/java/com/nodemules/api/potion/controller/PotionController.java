package com.nodemules.api.potion.controller;

import com.nodemules.api.potion.core.pot.PotionOperations;
import com.nodemules.api.potion.core.pot.PotionService;
import com.nodemules.api.potion.core.pot.bean.Potion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brent
 * @since 12/14/17.
 */
@RestController
@RequestMapping("/potion")
public class PotionController {

  private PotionOperations potionService;

  @Autowired
  public PotionController(PotionService potionService) {
    this.potionService = potionService;
  }

  @RequestMapping
  public Potion getRandomPotion() {
    return new Potion("fizz", 0xEFEFEF);
  }

  @RequestMapping("/brew")
  public void brewPotion(@RequestParam String name) {
    potionService.brewPotion(new Potion(name, 0));
  }
}
