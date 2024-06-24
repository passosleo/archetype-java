package dev.leopassos.archetype.infra.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.leopassos.archetype.application.services.auth.ITokenService;
import dev.leopassos.archetype.domain.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService implements ITokenService {
    private final long expiration;
    private final String issuer;
    private final Algorithm algorithm;

    public TokenService(
            @Value("${api.security.token.secret}") String secret,
            @Value("${api.security.token.expiration}") long expiration,
            @Value("${api.security.token.issuer}") String issuer
    ) {
        this.expiration = expiration;
        this.issuer = issuer;
        this.algorithm = Algorithm.HMAC256(secret);
    }

    @Override
    public String generateToken(User user) {
        LocalDateTime expiryDateTime = LocalDateTime.now().plusSeconds(expiration);
        Instant expiryInstant = expiryDateTime.toInstant(ZoneOffset.UTC);

        return JWT.create()
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(expiryInstant))
                .withIssuer(issuer)
                .withSubject(user.email())
                .withClaim("id", user.id())
                .withClaim("name", user.name())
                .withClaim("email", user.email())
                .withClaim("role", user.role().toString())
                .withClaim("enabled", user.enabled())
                .withClaim("createdAt", user.createdAt().toString())
                .withClaim("updatedAt", user.updatedAt().toString())
                .sign(algorithm);
    }

    @Override
    public String getSubject(String token) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    @Override
    public Boolean isTokenValid(String token) {
        try {
            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
