package dev.leopassos.archetype.presentation.dtos.login;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {
    private String username;
    private String password;
}
