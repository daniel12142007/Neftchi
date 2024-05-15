package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.Role;

public record UserResponse(Long id,
                           String email,
                           String jwt,
                           Role role) {
}