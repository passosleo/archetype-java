package dev.leopassos.archetype.application.usecases.login;

import dev.leopassos.archetype.presentation.dtos.login.LoginRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;

public interface ILoginUseCase {
    LoginResponseDTO execute(LoginRequestDTO data);
}
