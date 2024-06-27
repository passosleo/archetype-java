package dev.leopassos.archetype.application.usecases.signup;

import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.enums.Role;
import dev.leopassos.archetype.domain.repositories.IUserRepository;
import dev.leopassos.archetype.helpers.PasswordHasher;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupRequestDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupResponseDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupUseCase implements ISignupUseCase {

    private final IUserRepository userRepository;
    private final ITokenService ITokenService;

    @Override
    public SignupResponseDTO execute(SignupRequestDTO data) {
        User user = userRepository.save(new User(
                data.getName(),
                data.getEmail(),
                PasswordHasher.hash(data.getPassword()),
                Role.USER,
                true
        ));

        return SignupResponseDTO.builder()
                .user(UserInfoResponseDTO.builder()
                        .id(user.id())
                        .name(user.name())
                        .email(user.email())
                        .role(user.role())
                        .enabled(user.enabled())
                        .createdAt(user.createdAt())
                        .updatedAt(user.updatedAt())
                        .build())
                .login(LoginResponseDTO.builder()
                        .token(ITokenService.generateToken(user))
                        .type("Bearer")
                        .build())
                .build();
    }
}