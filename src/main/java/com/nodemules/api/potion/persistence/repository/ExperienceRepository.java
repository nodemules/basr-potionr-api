package com.nodemules.api.potion.persistence.repository;

import com.nodemules.api.potion.persistence.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/17/17.
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

}
