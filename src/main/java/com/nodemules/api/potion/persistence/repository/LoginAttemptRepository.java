package com.nodemules.api.potion.persistence.repository;

import com.nodemules.api.potion.persistence.domain.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/15/17.
 */
@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {

  int countByUserIdAndIpAddress(Long userId, String ipAddress);
}
