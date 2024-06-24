package dev.leopassos.archetype.application.usecases.user;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.repositories.IUserRepository;
import dev.leopassos.archetype.infra.mappers.UserMapper;
import dev.leopassos.archetype.infra.security.SecurityUtils;
import dev.leopassos.archetype.presentation.dtos.user.UserDetailsDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoUseCase implements IUserInfoUseCase {

    private final IUserRepository userRepository;

    @Override
    public UserInfoResponseDTO execute() {
        UserDetailsDTO authenticatedUser = SecurityUtils.getAuthenticatedUser();
        if (authenticatedUser != null) {
            Optional<User> userInfo = userRepository.findByEmail(authenticatedUser.getEmail());

            if (userInfo.isPresent()) {
                return UserMapper.toUserInfo(userInfo.get());
            }
        }

        throw new RuntimeException("User not found");
    }
}
