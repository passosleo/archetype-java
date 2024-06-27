package dev.leopassos.archetype.application.usecases.login.credentials;

import dev.leopassos.archetype.application.dtos.auth.AuthCredentialsDTO;
import dev.leopassos.archetype.application.services.auth.IAuthService;
import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.login.LoginCredentialsRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCredentialsUseCase implements ILoginCredentialsUseCase {

    private final IAuthService authService;
    private final ITokenService tokenService;

    @Override
    public LoginResponseDTO execute(LoginCredentialsRequestDTO data) {
        User authenticatedUser = authService.authenticate(
                AuthCredentialsDTO.builder()
                        .username(data.getEmail())
                        .password(data.getPassword())
                        .build()
        );
        String token = tokenService.generateToken(authenticatedUser);
        return LoginResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}
