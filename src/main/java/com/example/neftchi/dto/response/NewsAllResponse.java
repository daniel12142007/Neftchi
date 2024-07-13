package com.example.neftchi.dto.response;

import java.time.LocalDateTime;
public record NewsAllResponse(
        Long id,
        String headerImage,
        String title,
        String description,
        LocalDateTime date,
        boolean pdf,
        boolean video,
        boolean linkYoutube,
        String category
) {
}