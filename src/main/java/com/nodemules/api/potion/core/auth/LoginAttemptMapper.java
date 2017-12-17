package com.nodemules.api.potion.core.auth;

import com.nodemules.api.potion.core.auth.model.LoginAttempt;
import com.nodemules.api.potion.persistence.domain.LoginBlacklist;
import com.nodemules.api.potion.persistence.domain.LoginSuccess;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

/**
 * @author brent
 * @since 12/15/17.
 */
@Mapper(
    withIgnoreMissing = IgnoreMissing.ALL,
    withIoC = IoC.SPRING)
public interface LoginAttemptMapper {

  LoginAttempt toModel(com.nodemules.api.potion.persistence.domain.LoginAttempt entity);

  com.nodemules.api.potion.persistence.domain.LoginAttempt toAttemptEntity(LoginAttempt model);

  LoginBlacklist toBlacklistEntity(LoginAttempt model);

  LoginSuccess toSuccessEntity(LoginAttempt attempt);
}
