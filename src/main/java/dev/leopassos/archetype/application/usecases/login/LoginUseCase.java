package dev.leopassos.archetype.application.usecases.login;

import dev.leopassos.archetype.application.services.auth.IAuthService;
import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.presentation.dtos.login.CredentialsDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements ILoginUseCase {
    private final IAuthService authService;
    private final ITokenService tokenService;

    @Override
    public LoginResponseDTO execute(LoginRequestDTO data) {
        User authenticatedUser = authService.authenticate(
                CredentialsDTO.builder()
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
