package dev.leopassos.archetype.application.usecases.signup;

import dev.leopassos.archetype.presentation.dtos.signup.SignupRequestDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupResponseDTO;

public interface ISignupUseCase {
    SignupResponseDTO execute(SignupRequestDTO data);
}