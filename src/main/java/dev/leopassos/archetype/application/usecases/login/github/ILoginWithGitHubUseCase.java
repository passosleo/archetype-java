package dev.leopassos.archetype.application.usecases.login.github;

import dev.leopassos.archetype.presentation.dtos.login.github.LoginWithGitHubRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.github.LoginWithGitHubResponseDTO;

public interface ILoginWithGitHubUseCase {
    LoginWithGitHubResponseDTO execute(LoginWithGitHubRequestDTO data);
}