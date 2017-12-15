package com.nodemules.api.potion.controller;

import com.nodemules.api.potion.core.pot.Potion.Potion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brent
 * @since 12/14/17.
 */
@RestController
@RequestMapping("/potion")
public class PotionController {

  @RequestMapping
  public Potion getRandomPotion() {
    return new Potion("fizz", 0xEFEFEF);
  }
}
