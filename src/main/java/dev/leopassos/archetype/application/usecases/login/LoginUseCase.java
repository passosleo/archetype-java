package dev.leopassos.archetype.application.usecases.login;

import dev.leopassos.archetype.infra.mappers.UserMapper;
import dev.leopassos.archetype.infra.security.TokenService;
import dev.leopassos.archetype.presentation.dtos.login.LoginRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements ILoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginResponseDTO execute(LoginRequestDTO data) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        Authentication authentication = authenticationManager.authenticate(credentials);
        String token = tokenService.generateToken(UserMapper.fromUserDetails((UserDetailsDTO) authentication.getPrincipal()));
        return LoginResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}
