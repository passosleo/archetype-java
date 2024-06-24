package dev.leopassos.archetype.presentation.exceptions;

import dev.leopassos.archetype.presentation.dtos.generic.ErrorDTO;
import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.of(HttpStatus.NOT_FOUND, ErrorDTO.of(1, "Entity not found")));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<ValidationFieldError>>> handleBadRequestException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.of(HttpStatus.BAD_REQUEST, errors.stream().map(ValidationFieldError::new).toList()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleBadRequestException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.of(HttpStatus.BAD_REQUEST, ErrorDTO.of(1, ex.getMessage())));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleBadCredentialsException() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDTO.of(HttpStatus.UNAUTHORIZED, ErrorDTO.of(1, "Invalid credentials")));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDTO.of(HttpStatus.UNAUTHORIZED, ErrorDTO.of(1, "Authentication failed: " + ex.getMessage())));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ResponseDTO.of(HttpStatus.FORBIDDEN, ErrorDTO.of(1, "Access denied: " + ex.getMessage())));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ResponseDTO.of(HttpStatus.CONFLICT, ErrorDTO.of(1, "Error: " + ex.getMostSpecificCause())));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<ErrorDTO>> handleInternalServerError(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.of(HttpStatus.INTERNAL_SERVER_ERROR, ErrorDTO.of(1, "Error: " + ex.getLocalizedMessage())));
    }

    private record ValidationFieldError(String field, String message) {
        public ValidationFieldError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
