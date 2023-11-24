package com.offsidegaming.measuremonitoringservice.repository;

import com.offsidegaming.measuremonitoringservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
