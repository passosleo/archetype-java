package dev.leopassos.archetype.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheck {

    @GetMapping
    public ResponseEntity<Boolean> healthCheck() {
        return ResponseEntity.ok(true);
    }
}
