package com.nodemules.api.potion.persistence.repository;

import com.nodemules.api.potion.persistence.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/17/17.
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
