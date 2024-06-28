package dev.leopassos.archetype.application.usecases.login.linkedin;

import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;

public interface ILoginLinkedInUseCase {
    LoginResponseDTO execute(LoginProviderRequestDTO data);
}