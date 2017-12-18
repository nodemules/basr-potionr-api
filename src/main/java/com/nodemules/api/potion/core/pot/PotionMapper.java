package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.auth.UserMapper;
import com.nodemules.api.potion.core.pot.bean.Potion;
import com.nodemules.api.potion.core.pot.bean.PotionType;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import java.util.List;

/**
 * @author brent
 * @since 12/17/17.
 */
@Mapper(
    withIoC = IoC.SPRING,
    withCustom = {UserMapper.class}
)
public interface PotionMapper {

  @Maps(withCustomFields = {
      @Field(value = {"id", "potionTypeId"})
  })
  com.nodemules.api.potion.persistence.domain.PotionType asPotionType(PotionType bean);

  @Maps(withCustomFields = {
      @Field(value = {"id", "potionTypeId"})
  })
  PotionType asPotionTypeBean(com.nodemules.api.potion.persistence.domain.PotionType entity);

  List<PotionType> asPotionTypeBeans(
      List<com.nodemules.api.potion.persistence.domain.PotionType> entities);

  @Maps(
      withCustomFields = {
          @Field(value = {"id", "potionId"})
      }
  )
  com.nodemules.api.potion.persistence.domain.Potion toEntity(Potion potion);
}
