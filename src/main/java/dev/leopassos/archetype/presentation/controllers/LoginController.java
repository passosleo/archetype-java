package dev.leopassos.archetype.presentation.controllers;

import dev.leopassos.archetype.application.usecases.login.ILoginUseCase;
import dev.leopassos.archetype.presentation.dtos.login.LoginRequestDTO;
import dev.leopassos.archetype.presentation.dtos.login.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final ILoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        return ResponseEntity.ok(loginUseCase.execute(data));
    }
}