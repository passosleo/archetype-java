package dev.leopassos.archetype.infra.services.auth.facebook;

import dev.leopassos.archetype.application.clients.auth.IOAuth2Client;
import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.application.services.auth.IOAuth2Service;
import dev.leopassos.archetype.domain.entities.User;
import dev.leopassos.archetype.domain.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Qualifier("facebook")
@Service
public class FacebookIOAuth2Service implements IOAuth2Service {
    private final IOAuth2Client authClient;
    private final IUserRepository userRepository;

    public FacebookIOAuth2Service(@Qualifier("facebook") IOAuth2Client authClient, IUserRepository userRepository) {
        this.authClient = authClient;
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(OAuth2CredentialsDTO credentials) {
        String accessToken = authClient.getAccessToken(credentials);
        String userEmail = authClient.getUsername(accessToken);
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent() && Boolean.TRUE.equals(user.get().enabled())) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
