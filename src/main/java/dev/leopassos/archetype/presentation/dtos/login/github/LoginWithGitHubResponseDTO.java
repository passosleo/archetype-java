package dev.leopassos.archetype.presentation.dtos.login.github;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithGitHubResponseDTO {
    private String type;
    private String token;
}
