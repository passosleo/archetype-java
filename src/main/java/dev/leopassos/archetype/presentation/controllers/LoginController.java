package dev.leopassos.archetype.presentation.controllers;

import dev.leopassos.archetype.application.usecases.login.credentials.ILoginCredentialsUseCase;
import dev.leopassos.archetype.application.usecases.login.facebook.ILoginFacebookUseCase;
import dev.leopassos.archetype.application.usecases.login.github.ILoginGitHubUseCase;
import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginCredentialsRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginProviderRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final ILoginCredentialsUseCase loginWithCredentialsUseCase;
    private final ILoginGitHubUseCase loginWithGitHubUseCase;
    private final ILoginFacebookUseCase loginWithFacebookUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> loginWithCredentials(@RequestBody @Valid LoginCredentialsRequestDTO data) {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, loginWithCredentialsUseCase.execute(data)));
    }

    @PostMapping("/github")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> loginWithGitHub(@RequestBody @Valid LoginProviderRequestDTO data) {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, loginWithGitHubUseCase.execute(data)));
    }

    @PostMapping("/facebook")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> loginWithFacebook(@RequestBody @Valid LoginProviderRequestDTO data) {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, loginWithFacebookUseCase.execute(data)));
    }
}