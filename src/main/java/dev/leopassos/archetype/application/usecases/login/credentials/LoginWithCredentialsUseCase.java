package dev.leopassos.archetype.application.usecases.login.credentials;

import dev.leopassos.archetype.application.services.auth.IAuthService;
import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.auth.AuthCredentialsDTO;
import dev.leopassos.archetype.presentation.dtos.login.credentials.LoginWithCredentialsRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.credentials.LoginWithCredentialsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginWithCredentialsUseCase implements ILoginWithCredentialsUseCase {

    private final IAuthService authService;
    private final ITokenService tokenService;

    @Override
    public LoginWithCredentialsResponseDTO execute(LoginWithCredentialsRequestDTO data) {
        User authenticatedUser = authService.authenticate(
                AuthCredentialsDTO.builder()
                        .username(data.getEmail())
                        .password(data.getPassword())
                        .build()
        );
        String token = tokenService.generateToken(authenticatedUser);
        return LoginWithCredentialsResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}
