package dev.leopassos.archetype.infra.dtos.auth.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleUserEmailDTO {
    private String sub;
    private String picture;
    private String email;
    @JsonProperty("email_verified")
    private boolean emailVerified;
}
