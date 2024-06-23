package dev.leopassos.archetype.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        log.info("Swagger available at http://localhost:8080/swagger-ui/index.html");
        final Parameter acceptLanguageHeader = new Parameter()
                .in(SecurityScheme.In.HEADER.toString())
                .name("Accept-Language")
                .required(false)
                .schema(new StringSchema()._default("pt-br"));
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                        .addParameters("Accept-Language", acceptLanguageHeader)
                ).info(new Info().title("archetype").description("archetype").version("1.0.0"));
    }
}
