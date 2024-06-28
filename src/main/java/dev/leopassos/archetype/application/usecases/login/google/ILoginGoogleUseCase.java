package dev.leopassos.archetype.application.usecases.login.google;

import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;

public interface ILoginGoogleUseCase {
    LoginResponseDTO execute(LoginProviderRequestDTO data);
}
