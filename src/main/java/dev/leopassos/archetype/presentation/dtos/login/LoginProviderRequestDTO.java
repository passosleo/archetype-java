package dev.leopassos.archetype.presentation.dtos.login;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginProviderRequestDTO {
    @NotBlank
    private String code;
    @NotBlank
    private String redirectUri;
}
