package dev.leopassos.archetype.presentation.dtos.signup;

import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserInfoResponseDTO;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponseDTO {
    UserInfoResponseDTO user;
    LoginResponseDTO login;
}
