package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.domain.entities.User;

public interface ITokenService {
    String generateToken(User user);

    String getSubject(String token);

    Boolean isTokenValid(String token);
}
