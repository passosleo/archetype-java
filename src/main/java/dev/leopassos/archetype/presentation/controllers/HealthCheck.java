package dev.leopassos.archetype.presentation.controllers;

import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheck {

    @GetMapping
    public ResponseEntity<ResponseDTO<Boolean>> healthCheck() {
        return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, true));
    }
}
