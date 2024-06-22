package dev.leopassos.archetype.presentation.dtos.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @Email
    private String email;
    @NotBlank
    private String password;
}
