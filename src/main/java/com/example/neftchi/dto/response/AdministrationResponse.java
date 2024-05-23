package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.Language;

public record AdministrationResponse(Long id,
                                     String fullName,
                                     String description,
                                     String image,
                                     Language language) {
}