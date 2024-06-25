package dev.leopassos.archetype.application.usecases.login.credentials;

import dev.leopassos.archetype.presentation.dtos.login.credentials.LoginWithCredentialsRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.credentials.LoginWithCredentialsResponseDTO;

public interface ILoginWithCredentialsUseCase {
    LoginWithCredentialsResponseDTO execute(LoginWithCredentialsRequestDTO data);
}
