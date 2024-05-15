package com.example.neftchi.service;

import com.example.neftchi.config.JwtUtils;
import com.example.neftchi.dto.request.AuthRequest;
import com.example.neftchi.dto.response.UserResponse;
import com.example.neftchi.exception.NotFoundEmail;
import com.example.neftchi.model.Users;
import com.example.neftchi.model.enums.Role;
import com.example.neftchi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(AuthRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User exists email");
        return map(userRepository.save(user));
    }

    public UserResponse login(AuthRequest request) {
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(NotFoundEmail::new);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return map(user);
    }

    private UserResponse map(Users user) {
        return new UserResponse(user.getId(),
                user.getEmail(),
                jwtUtils.generateToken(user.getEmail()),
                user.getRole());
    }
}