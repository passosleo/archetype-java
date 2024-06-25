package dev.leopassos.archetype.presentation.dtos.auth;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredentialsDTO {
    private String username;
    private String password;
}
