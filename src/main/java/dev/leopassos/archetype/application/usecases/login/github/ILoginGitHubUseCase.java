package dev.leopassos.archetype.application.usecases.login.github;

import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;

public interface ILoginGitHubUseCase {
    LoginResponseDTO execute(LoginProviderRequestDTO data);
}