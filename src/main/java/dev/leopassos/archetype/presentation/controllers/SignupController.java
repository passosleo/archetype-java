package dev.leopassos.archetype.presentation.controllers;

import dev.leopassos.archetype.application.usecases.signup.ISignupUseCase;
import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupRequestDTO;
import dev.leopassos.archetype.presentation.dtos.signup.SignupResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/signup")
@RequiredArgsConstructor
public class SignupController {

    private final ISignupUseCase signupUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<SignupResponseDTO>> signup(@RequestBody @Valid SignupRequestDTO data) {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, signupUseCase.execute(data)));
    }
}