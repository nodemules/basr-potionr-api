package com.nodemules.api.potion.core.currency;

import com.github.javafaker.Faker;
import com.nodemules.api.potion.core.auth.UserMapper;
import com.nodemules.api.potion.core.auth.bean.User;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import com.nodemules.api.potion.persistence.domain.Currency;
import com.nodemules.api.potion.persistence.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author brent
 * @since 12/17/17.
 */
@Slf4j
@Service
public class CurrencyService implements CurrencyOperations {

  private static final Faker faker = new Faker();
  private CurrencyRepository currencyRepository;
  private UserMapper userMapper;

  @Autowired
  public CurrencyService(CurrencyRepository currencyRepository, UserMapper userMapper) {
    this.currencyRepository = currencyRepository;
    this.userMapper = userMapper;
  }

  @Override
  public void grantUserCurrencyForPotionBrewed(User user, PotionType type, boolean isUnique) {
    Currency curr = new Currency();
    int level = faker.number().numberBetween(1, 25);

    int amount = getCurrencyForPotionType(type, level);

    if (isUnique) {
      Float f = amount * 1.35f;
      amount = f.intValue();
    }

    log.info("Granting {} currency to User: {} (Level: {})", amount, user.getUsername(), level);
    curr.setUser(userMapper.toEntity(user));
    curr.setAmount(amount);
    currencyRepository.save(curr);
  }

  private int getCurrencyForPotionType(PotionType type, int level) {
    int rand = faker.number().numberBetween(1, 255);
    Float f = rand * type.getXpMultiplier() * level;
    return f.intValue();
  }
}
