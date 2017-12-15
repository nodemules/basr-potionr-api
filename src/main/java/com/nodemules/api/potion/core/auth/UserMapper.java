package com.nodemules.api.potion.core.auth;

import com.nodemules.api.potion.core.auth.bean.Permission;
import com.nodemules.api.potion.core.auth.bean.Role;
import com.nodemules.api.potion.core.auth.bean.User;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;

/**
 * @author brent
 * @since 12/14/17.
 */
@Mapper(withIgnoreMissing = IgnoreMissing.ALL, withIoC = IoC.SPRING)
public interface UserMapper {

  User toBean(com.nodemules.api.potion.persistence.domain.User entity);

  Role toBean(com.nodemules.api.potion.persistence.domain.Role entity);

  @Maps(withCustomFields = {@Field(value = {"authority", "name"})})
  Permission toBean(com.nodemules.api.potion.persistence.domain.Permission entity);

  com.nodemules.api.potion.persistence.domain.User toEntity(User user);

  com.nodemules.api.potion.persistence.domain.Role toEntity(Role role);

  @Maps(withCustomFields = {@Field(value = {"name", "authority"})})
  com.nodemules.api.potion.persistence.domain.Permission toEntity(Permission permission);
}
