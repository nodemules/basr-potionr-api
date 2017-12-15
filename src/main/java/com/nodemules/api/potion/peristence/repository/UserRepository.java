package com.nodemules.api.potion.peristence.repository;

import com.nodemules.api.potion.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author brent
 * @since 12/14/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(
      value = "SELECT * FROM user WHERE username = ?1 OR email = ?1",
      nativeQuery = true
  )
  User findByUsernameOrEmail(String login);

  @Query(
      value = "SELECT * FROM user WHERE username = ?1 OR email = ?2",
      nativeQuery = true
  )
  User findByUsernameOrEmail(String username, String email);
}
