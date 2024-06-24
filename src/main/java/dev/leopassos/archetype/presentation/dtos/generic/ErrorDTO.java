package dev.leopassos.archetype.presentation.dtos.generic;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private int errorCode;
    private String reason;

    public static ErrorDTO of(int errorCode, String reason) {
        return ErrorDTO.builder().errorCode(errorCode).reason(reason).build();
    }
}
