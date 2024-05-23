package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.StatusAppeal;

public record AppealResponseAll(Long id,
                                String fullName,
                                String email,
                                String phoneNumber,
                                StatusAppeal status) {
}