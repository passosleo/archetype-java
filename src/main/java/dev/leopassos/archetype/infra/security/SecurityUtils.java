package dev.leopassos.archetype.infra.security;

import dev.leopassos.archetype.presentation.dtos.user.UserDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserDetailsDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsDTO) {
            return (UserDetailsDTO) authentication.getPrincipal();
        }
        return null;
    }
}
