package dev.leopassos.archetype.infra.services.auth.credentials;

import dev.leopassos.archetype.application.services.auth.IAuthService;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.infra.mappers.UserMapper;
import dev.leopassos.archetype.presentation.dtos.auth.AuthCredentialsDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialsAuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;

    @Override
    public User authenticate(AuthCredentialsDTO credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
        );
        return UserMapper.fromUserDetails((UserDetailsDTO) authentication.getPrincipal());
    }
}
