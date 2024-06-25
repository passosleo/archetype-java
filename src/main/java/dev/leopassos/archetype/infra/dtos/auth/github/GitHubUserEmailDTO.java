package dev.leopassos.archetype.infra.dtos.auth.github;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUserEmailDTO {
    private String email;
    private Boolean primary;
    private Boolean verified;
    private String visibility;
}
