package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.auth.OAuth2CredentialsDTO;

public interface IOAuth2Service {
    User authenticate(OAuth2CredentialsDTO credentials);
}
