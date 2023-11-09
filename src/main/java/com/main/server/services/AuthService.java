package com.main.server.services;

import com.main.server.configuration.jwt.JwtUtils;
import com.main.server.configuration.security.services.UserDetailsImpl;
import com.main.server.models.entities.Role;
import com.main.server.models.entities.User;
import com.main.server.models.enumerations.EGender;
import com.main.server.models.enumerations.ERole;
import com.main.server.models.request.LoginRequest;
import com.main.server.models.request.SignUpRequest;
import com.main.server.models.response.LoginResponse;
import com.main.server.models.response.Response;
import com.main.server.repositories.RoleRepository;
import com.main.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    public boolean checkUserExisting(String email){
        return userRepository.existsByEmail(email);
    }

    public Object createUser(SignUpRequest signUpRequest) {
        if(!checkUserExisting(signUpRequest.getEmail())) {
            User user = new User(
                    signUpRequest.getName(),
                    signUpRequest.getBirth(),
                    EGender.valueOf(signUpRequest.getGender()),
                    signUpRequest.getPhone(),
                    signUpRequest.getAddress(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword())
            );

            if(signUpRequest.getRole() != null && !signUpRequest.getRole().isEmpty()) {
                Role role = roleRepository.findByName(ERole.valueOf(signUpRequest.getRole()));
                user.setRole(role);
            }
            else {
                user.setRole(roleRepository.findByName(ERole.ROLE_USER));
            }

            try {
                userRepository.save(user);
                return ResponseEntity.ok(new Response("Tạo tài khoản mới thành công!", user));
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body(new Response(e.getMessage(), null));
            }
        }
        else {
            return ResponseEntity.badRequest().body(new Response("Email đã tồn tại!", null));
        }
    }
    public Object authentication(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new Response("Xác thực thành công!", new LoginResponse(jwtUtils.generateToken(authentication), ((UserDetailsImpl) authentication.getPrincipal()).getId(), authentication.getAuthorities().stream().toList().get(0).toString())));
    }
}
