package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.application.dtos.auth.AuthCredentialsDTO;
import dev.leopassos.archetype.domain.entities.User;

public interface IAuthService {
    User authenticate(AuthCredentialsDTO credentials);
}
