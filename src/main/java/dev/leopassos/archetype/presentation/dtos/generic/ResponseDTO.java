package dev.leopassos.archetype.presentation.dtos.generic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    @Schema(description = "HTTP status code", example = "200")
    private int status;
    @Schema(description = "HTTP status message", example = "OK")
    private String message;
    private T data;

    public ResponseDTO(HttpStatus status) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public ResponseDTO(HttpStatus status, T data) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
        this.data = data;
    }

    public ResponseDTO(HttpStatus status, String message, T data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDTO<T> of(HttpStatus status) {
        return new ResponseDTO<>(status);
    }


    public static <T> ResponseDTO<T> of(HttpStatus status, T data) {
        return new ResponseDTO<>(status, data);
    }

    public static <T> ResponseDTO<T> of(HttpStatus status, String message, T data) {
        return new ResponseDTO<>(status, message, data);
    }
}
