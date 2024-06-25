package dev.leopassos.archetype.application.clients.auth;

import dev.leopassos.archetype.presentation.dtos.auth.OAuth2CredentialsDTO;

public interface IOAuth2Client {
    String getAccessToken(OAuth2CredentialsDTO credentials);

    String getUsername(String accessToken);
}
