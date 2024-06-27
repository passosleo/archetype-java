package dev.leopassos.archetype.infra.dtos.auth.facebook;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacebookUserEmailDTO {
    private String id;
    private String name;
    private String email;
}
