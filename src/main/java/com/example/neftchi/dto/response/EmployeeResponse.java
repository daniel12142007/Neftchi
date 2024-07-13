package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.Language;

import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id,
        String image,
        String fullName,
        String jobTitle,
        String description,
        LocalDateTime localDateTime,
        Language language
) {
}