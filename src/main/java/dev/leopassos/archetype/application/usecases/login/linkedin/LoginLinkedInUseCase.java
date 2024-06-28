package dev.leopassos.archetype.application.usecases.login.linkedin;

import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.application.services.auth.IOAuth2Service;
import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.enums.OAuth2Provider;
import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginLinkedInUseCase implements ILoginLinkedInUseCase {

    private final IOAuth2Service authService;
    private final ITokenService tokenService;

    @Value("${spring.security.oauth2.client.registration.linkedin.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.linkedin.client-secret}")
    private String clientSecret;

    @Override
    public LoginResponseDTO execute(LoginProviderRequestDTO data) {
        User authenticatedUser = authService.authenticate(
                OAuth2Provider.LINKEDIN,
                OAuth2CredentialsDTO.builder()
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .code(data.getCode())
                        .redirectUri(data.getRedirectUri())
                        .grantType("authorization_code")
                        .build()
        );
        String token = tokenService.generateToken(authenticatedUser);
        return LoginResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}
