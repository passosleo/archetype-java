package dev.leopassos.archetype.infra.dtos.auth.linkedin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedInUserEmailDTO {
    private String sub;
    @JsonProperty("email_verified")
    private boolean emailVerified;
    private String name;
    private LocaleDTO locale;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    private String email;
    private String picture;
}
