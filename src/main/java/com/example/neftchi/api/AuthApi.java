package com.example.neftchi.api;

import com.example.neftchi.dto.request.AuthRequest;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.dto.response.UserResponse;
import com.example.neftchi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    public UserResponse authenticated(@Valid @RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @GetMapping("send/code/{email}")
    public SystemMessage getCode(@PathVariable @Valid @Email String email) {
        return authService.getCode(email);
    }

    @GetMapping("check/code/{email}")
    public SystemMessage checkCode(@PathVariable @Valid @Email String email,
                                   @RequestParam String code) {
        return authService.checkCode(email, code);
    }

    @PutMapping("reset/password/{email}")
    public UserResponse resetPassword(@PathVariable @Valid @Email String email,
                                      @RequestParam String password1,
                                      @RequestParam String password2
    ) {
        return authService.resetPassword(email, password1, password2);
    }
}
