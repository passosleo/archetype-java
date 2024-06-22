package dev.leopassos.archetype.presentation.dtos.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
}
