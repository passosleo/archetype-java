package dev.leopassos.archetype.application.usecases.signup;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.enums.Role;
import dev.leopassos.archetype.domain.repositories.IUserRepository;
import dev.leopassos.archetype.helpers.PasswordHasher;
import dev.leopassos.archetype.infra.security.TokenService;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupRequestDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupResponseDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupUseCase implements ISignupUseCase {

    private final IUserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public SignupResponseDTO execute(SignupRequestDTO data) {
        Optional<User> userOptional = userRepository.findByEmail(data.getEmail());

        if (userOptional.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = userRepository.save(new User(
                data.getName(),
                data.getEmail(),
                PasswordHasher.hash(data.getPassword()),
                Role.USER,
                true
        ));

        return SignupResponseDTO.builder()
                .user(UserResponseDTO.builder()
                        .id(user.id())
                        .name(user.name())
                        .email(user.email())
                        .role(user.role())
                        .enabled(user.enabled())
                        .createdAt(user.createdAt())
                        .updatedAt(user.updatedAt())
                        .build())
                .login(LoginResponseDTO.builder()
                        .token(tokenService.generateToken(user))
                        .type("Bearer")
                        .build())
                .build();
    }
}