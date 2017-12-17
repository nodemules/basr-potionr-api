package com.nodemules.api.potion.core.pot;

import com.nodemules.api.potion.core.pot.bean.PotionType;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import java.util.List;

/**
 * @author brent
 * @since 12/17/17.
 */
@Mapper(
    withIoC = IoC.SPRING,
    withCustomFields = {
        @Field(value = {"id", "potionTypeId"})
    }
)
public interface PotionMapper {

  PotionType asPotionTypeBean(com.nodemules.api.potion.persistence.domain.PotionType entity);

  List<PotionType> asPotionTypeBeans(
      List<com.nodemules.api.potion.persistence.domain.PotionType> entities);

}
