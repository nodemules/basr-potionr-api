package com.nodemules.api.potion.persistence.repository;

import com.nodemules.api.potion.persistence.domain.LoginAttempt;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/15/17.
 */
@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

  int countByUserUserIdAndIpAddressAndAttemptTimeAfter(Long userId, String ipAddress,
      Date attemptTime);
}
