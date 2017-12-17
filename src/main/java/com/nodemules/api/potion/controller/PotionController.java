package com.nodemules.api.potion.controller;

import com.nodemules.api.potion.core.pot.PotionOperations;
import com.nodemules.api.potion.core.pot.PotionService;
import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  @RequestMapping(value = "/type")
  public List<PotionType> getPotionTypes() {
    return potionService.getPotionTypes();
  }

  @RequestMapping(value = "/brew", method = RequestMethod.POST)
  public void brewPotion(@RequestBody Potion potion) {
    potionService.brewPotion(potion);
  }
}
