package com.example.neftchi.service;

import com.example.neftchi.config.JwtUtils;
import com.example.neftchi.dto.request.AuthRequest;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.dto.response.UserResponse;
import com.example.neftchi.exception.NotFoundEmail;
import com.example.neftchi.model.Users;
import com.example.neftchi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public UserResponse login(AuthRequest request) {
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(NotFoundEmail::new);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return map(user);
    }

    public SystemMessage getCode(String email) {
        Users users = userRepository.findByEmail(email).orElseThrow(NotFoundEmail::new);
        String code = randomNumber();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Don't show the code to anyone");
            message.setText(code);
            javaMailSender.send(message);
            users.setCode(code);
            userRepository.save(users);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred when sending the code to email");
        }
        return new SystemMessage("Code sent successfully");
    }

    public SystemMessage checkCode(String email,
                                   String code) {
        Users users = userRepository.findByEmail(email).orElseThrow(NotFoundEmail::new);
        if (users.getCode().equals(code)) {
            users.setCode(null);
            userRepository.save(users);
            return new SystemMessage("The entered code is correct");
        }
        throw new RuntimeException("The entered code is incorrect");
    }

    public UserResponse resetPassword(String email,
                                      String password,
                                      String password2) {
        Users users = userRepository.findByEmail(email).orElseThrow(NotFoundEmail::new);
        if (!password.equals(password2))
            throw new RuntimeException("Password mismatch");
        users.setPassword(passwordEncoder.encode(password));
        userRepository.save(users);
        return map(users);
    }

    private String randomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10, 99)) +
               String.valueOf(random.nextInt(10, 99)) +
               String.valueOf(random.nextInt(10, 99));
    }

    private UserResponse map(Users user) {
        return new UserResponse(user.getId(),
                user.getEmail(),
                jwtUtils.generateToken(user.getEmail()),
                user.getRole());
    }
}