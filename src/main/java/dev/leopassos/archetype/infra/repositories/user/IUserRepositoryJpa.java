package dev.leopassos.archetype.infra.repositories.user;

import dev.leopassos.archetype.infra.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
