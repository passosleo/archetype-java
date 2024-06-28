package dev.leopassos.archetype.application.clients.auth;

import dev.leopassos.archetype.domain.enums.OAuth2Provider;

public interface IOAuth2ClientFactory {
    IOAuth2Client create(OAuth2Provider provider);
}
