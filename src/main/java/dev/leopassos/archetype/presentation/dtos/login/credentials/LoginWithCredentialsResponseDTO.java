package dev.leopassos.archetype.presentation.dtos.login.credentials;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginWithCredentialsResponseDTO {
    private String type;
    private String token;
}
