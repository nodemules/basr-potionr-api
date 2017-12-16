package com.nodemules.api.potion.persistence.repository;

import com.nodemules.api.potion.persistence.domain.LoginSuccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/16/17.
 */
@Repository
public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Long> {

}
