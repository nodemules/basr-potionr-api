package com.nodemules.api.potion.core.experience;

import com.github.javafaker.Faker;
import com.nodemules.api.potion.core.auth.UserMapper;
import com.nodemules.api.potion.core.auth.bean.User;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import com.nodemules.api.potion.persistence.domain.Experience;
import com.nodemules.api.potion.persistence.repository.ExperienceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author brent
 * @since 12/17/17.
 */
@Slf4j
@Service
public class ExperienceService implements ExperienceOperations {

  private static final Faker faker = new Faker();
  private ExperienceRepository experienceRepository;
  private UserMapper userMapper;

  @Autowired
  public ExperienceService(ExperienceRepository experienceRepository, UserMapper userMapper) {
    this.experienceRepository = experienceRepository;
    this.userMapper = userMapper;
  }

  @Override
  public void grantUserExperienceForPotionBrewed(User user, PotionType type, boolean isUnique) {
    Experience exp = new Experience();
    int level = faker.number().numberBetween(1, 25);

    int xp = getExperienceForPotionType(type, level);

    if (isUnique) {
      Float f = xp * 1.35f;
      xp = f.intValue();
    }

    log.info("Granting {} experience to User: {} (Level: {})", xp, user.getUsername(), level);
    exp.setUser(userMapper.toEntity(user));
    exp.setXp(xp);
    experienceRepository.save(exp);
  }

  private int getExperienceForPotionType(PotionType type, int level) {
    int rand = faker.number().numberBetween(1, 255);
    Float f = rand * type.getXpMultiplier() * level;
    return f.intValue();
  }
}
