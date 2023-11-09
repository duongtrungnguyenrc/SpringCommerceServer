package com.main.server.controller;

import com.main.server.models.request.LoginRequest;
import com.main.server.models.request.SignUpRequest;
import com.main.server.models.response.Response;
import com.main.server.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        return authService.authentication(loginRequest);
    }

    @PostMapping("/register")
    public Object register(@RequestBody SignUpRequest signUpRequest) {
        return authService.createUser(signUpRequest);
    }
}
