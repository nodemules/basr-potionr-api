package com.nodemules.api.potion.persistence.repository;

import com.nodemules.api.potion.persistence.domain.LoginBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/15/17.
 */
@Repository
public interface LoginBlacklistRepository extends JpaRepository<LoginBlacklist, Long> {

  LoginBlacklist findByUserUserIdAndIpAddress(Long userId, String ipAddress);
}
