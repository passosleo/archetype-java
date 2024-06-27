package dev.leopassos.archetype.application.usecases.login.credentials;

import dev.leopassos.archetype.presentation.dtos.login.LoginCredentialsRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;

public interface ILoginCredentialsUseCase {
    LoginResponseDTO execute(LoginCredentialsRequestDTO data);
}
