package dev.leopassos.archetype.infra.security;

import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.repositories.IUserRepository;
import dev.leopassos.archetype.infra.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isPresent()) {
            return UserMapper.toUserDetails(user.get());
        }

        throw new UsernameNotFoundException("User not found");
    }
}
