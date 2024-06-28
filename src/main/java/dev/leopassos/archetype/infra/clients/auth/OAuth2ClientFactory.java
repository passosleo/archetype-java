package dev.leopassos.archetype.infra.clients.auth;

import dev.leopassos.archetype.application.clients.auth.IOAuth2Client;
import dev.leopassos.archetype.application.clients.auth.IOAuth2ClientFactory;
import dev.leopassos.archetype.domain.enums.OAuth2Provider;
import dev.leopassos.archetype.infra.clients.auth.facebook.FacebookOAuth2Client;
import dev.leopassos.archetype.infra.clients.auth.github.GitHubOAuth2Client;
import dev.leopassos.archetype.infra.clients.auth.linkedin.LinkedInOAuth2Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2ClientFactory implements IOAuth2ClientFactory {

    private final ApplicationContext applicationContext;

    @Override
    public IOAuth2Client create(OAuth2Provider provider) {
        return switch (provider) {
            case FACEBOOK -> applicationContext.getBean(FacebookOAuth2Client.class);
            case GITHUB -> applicationContext.getBean(GitHubOAuth2Client.class);
            case LINKEDIN -> applicationContext.getBean(LinkedInOAuth2Client.class);
            default -> throw new IllegalArgumentException("Invalid provider");
        };
    }
}
