package dev.leopassos.archetype.presentation.dtos.login.github;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithGitHubRequestDTO {
    @NotBlank
    private String clientId;
    @NotBlank
    private String code;
    @NotBlank
    private String redirectUri;
}
