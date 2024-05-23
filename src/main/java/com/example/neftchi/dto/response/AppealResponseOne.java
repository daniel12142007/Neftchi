package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.StatusAppeal;

public record AppealResponseOne(Long id,
                                String fullName,
                                String briefComplaint,
                                String description,
                                String phoneNumber,
                                String email,
                                StatusAppeal status,
                                String fileName,
                                String answer,
                                boolean answered) {
}