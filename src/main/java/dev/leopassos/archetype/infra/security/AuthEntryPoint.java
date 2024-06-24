package dev.leopassos.archetype.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.leopassos.archetype.presentation.dtos.generic.ErrorDTO;
import dev.leopassos.archetype.presentation.dtos.generic.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpStatus.FORBIDDEN.value());
        ResponseDTO<ErrorDTO> response = ResponseDTO.of(HttpStatus.FORBIDDEN, ErrorDTO.of(1, "Access denied"));
        res.getWriter().write(objectMapper.writeValueAsString(response));
    }
}
