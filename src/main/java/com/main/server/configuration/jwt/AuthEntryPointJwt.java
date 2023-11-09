package com.main.server.configuration.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.main.server.models.response.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        String message = "";
        if(authException.getMessage().equals("Bad credentials")){
            message = "Sai email hoặc mật khẩu";
        }
        else if (authException.getMessage().equals("Full authentication is required to access this resource")){
            message = "vui lòng đăng nhập";
        }
        body.put("error", "Unauthorized");
        body.put("path", request.getServletPath());
        Response authResponse = new Response(
                message,
                false
        );

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), authResponse);
    }
}
