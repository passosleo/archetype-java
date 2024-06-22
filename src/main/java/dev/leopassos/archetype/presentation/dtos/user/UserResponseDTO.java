package dev.leopassos.archetype.presentation.dtos.user;

import dev.leopassos.archetype.domain.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    Long id;
    String name;
    String email;
    Role role;
    boolean enabled;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
