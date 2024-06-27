package dev.leopassos.archetype.presentation.dtos.login;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String type;
    private String token;
}
