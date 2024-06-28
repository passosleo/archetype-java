package dev.leopassos.archetype.application.services.auth;

import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.enums.OAuth2Provider;

public interface IOAuth2Service {
    User authenticate(OAuth2Provider provider, OAuth2CredentialsDTO credentials);
}
