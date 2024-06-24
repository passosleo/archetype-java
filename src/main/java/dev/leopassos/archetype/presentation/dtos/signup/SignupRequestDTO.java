package dev.leopassos.archetype.presentation.dtos.signup;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(format = "email")
    private String email;
    @NotBlank
    private String password;
}
