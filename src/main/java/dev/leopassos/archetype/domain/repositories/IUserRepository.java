package dev.leopassos.archetype.domain.repositories;

import dev.leopassos.archetype.domain.entities.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByEmail(String email);

    User save(User user);
}
