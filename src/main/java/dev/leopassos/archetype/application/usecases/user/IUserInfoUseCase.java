package dev.leopassos.archetype.application.usecases.user;

import dev.leopassos.archetype.presentation.dtos.user.UserInfoResponseDTO;

public interface IUserInfoUseCase {
    UserInfoResponseDTO execute();
}