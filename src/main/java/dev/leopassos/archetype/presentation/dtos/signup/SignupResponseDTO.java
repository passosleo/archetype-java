package dev.leopassos.archetype.presentation.dtos.signup;

import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserResponseDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponseDTO {
    UserResponseDTO user;
    LoginResponseDTO login;
}
