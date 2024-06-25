package dev.leopassos.archetype.application.dtos.auth;

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
