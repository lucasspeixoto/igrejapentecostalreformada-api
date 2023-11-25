package com.platform.igrejapentecostalreformadaapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.igrejapentecostalreformadaapi.data.vo.ErrorDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;

@Service
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // set the response status code
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // set the response content type as json
        response.setContentType("application/json");

        // set up the response body as ErrorDetails clas
        ErrorDetails unauthorized = new ErrorDetails(
                Instant.now(),
                "Username, Email or Password is incorrect!",
                "",
                HttpServletResponse.SC_UNAUTHORIZED
        );

        // write the response body
        objectMapper.writeValue(response.getOutputStream(), unauthorized);

        // commit the response
        response.flushBuffer();

    }
}