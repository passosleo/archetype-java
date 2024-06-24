package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.login.CredentialsDTO;

public interface IAuthService {
    User authenticate(CredentialsDTO credentials);
}
