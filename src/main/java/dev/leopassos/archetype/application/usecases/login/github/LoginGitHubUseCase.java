package dev.leopassos.archetype.application.usecases.login.github;

import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.application.services.auth.IOAuth2Service;
import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginGitHubUseCase implements ILoginGitHubUseCase {

    private final IOAuth2Service authService;
    private final ITokenService tokenService;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private String clientSecret;

    public LoginGitHubUseCase(@Qualifier("github") IOAuth2Service authService, ITokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponseDTO execute(LoginProviderRequestDTO data) {
        User authenticatedUser = authService.authenticate(
                OAuth2CredentialsDTO.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .code(data.getCode())
                        .redirectUri(data.getRedirectUri())
                        .build()
        );
        String token = tokenService.generateToken(authenticatedUser);
        return LoginResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}
