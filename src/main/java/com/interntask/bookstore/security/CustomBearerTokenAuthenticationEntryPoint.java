package com.interntask.bookstore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interntask.bookstore.model.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
@Component
public class CustomBearerTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    public CustomBearerTokenAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if (request.getAttribute("exception") != null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Expired JWT token");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("JWT has expired")
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
