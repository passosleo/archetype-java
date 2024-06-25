package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.auth.AuthCredentialsDTO;

public interface IAuthService {
    User authenticate(AuthCredentialsDTO credentials);
}
