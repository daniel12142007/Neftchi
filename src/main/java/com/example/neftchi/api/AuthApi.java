package com.example.neftchi.api;

import com.example.neftchi.dto.request.AuthRequest;
import com.example.neftchi.dto.response.UserResponse;
import com.example.neftchi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public UserResponse authenticated(@Valid @RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
