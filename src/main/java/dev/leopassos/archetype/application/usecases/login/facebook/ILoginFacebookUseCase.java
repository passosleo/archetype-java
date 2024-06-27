package dev.leopassos.archetype.application.usecases.login.facebook;

import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;

public interface ILoginFacebookUseCase {
    LoginResponseDTO execute(LoginProviderRequestDTO data);
}
