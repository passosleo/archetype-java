package dev.leopassos.archetype.domain.entities;

import dev.leopassos.archetype.domain.enums.Role;

import java.time.LocalDateTime;

public record User(
        Long id,
        String name,
        String email,
        String password,
        Role role,
        boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public User(String name, String email, String password, Role role, boolean enabled) {
        this(null, name, email, password, role, enabled, null, null);
    }
}
