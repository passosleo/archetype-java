package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.domain.entities.User;

public interface IOAuth2Service {
    User authenticate(OAuth2CredentialsDTO credentials);
}
