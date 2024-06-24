package dev.leopassos.archetype.presentation.controllers;

import dev.leopassos.archetype.application.usecases.user.IUserInfoUseCase;
import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import dev.leopassos.archetype.presentation.dtos.user.UserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserInfoUseCase userInfoUseCase;

    @GetMapping("/info")
    public ResponseEntity<ResponseDTO<UserInfoResponseDTO>> userInfo() {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, userInfoUseCase.execute()));
    }
}
