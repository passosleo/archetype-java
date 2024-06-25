package dev.leopassos.archetype.presentation.controllers;

import dev.leopassos.archetype.application.usecases.login.credentials.ILoginWithCredentialsUseCase;
import dev.leopassos.archetype.application.usecases.login.github.ILoginWithGitHubUseCase;
import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import dev.leopassos.archetype.presentation.dtos.login.credentials.LoginWithCredentialsRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.credentials.LoginWithCredentialsResponseDTO;
import dev.leopassos.archetype.presentation.dtos.login.github.LoginWithGitHubRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.github.LoginWithGitHubResponseDTO;
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

    private final ILoginWithCredentialsUseCase loginWithCredentialsUseCase;
    private final ILoginWithGitHubUseCase loginWithGitHubUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<LoginWithCredentialsResponseDTO>> loginWithCredentials(@RequestBody @Valid LoginWithCredentialsRequestDTO data) {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, loginWithCredentialsUseCase.execute(data)));
    }

    @PostMapping("/github")
    public ResponseEntity<ResponseDTO<LoginWithGitHubResponseDTO>> loginWithGitHub(@RequestBody @Valid LoginWithGitHubRequestDTO data) {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, loginWithGitHubUseCase.execute(data)));
    }
}