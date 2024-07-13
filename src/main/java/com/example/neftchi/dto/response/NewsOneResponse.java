package com.example.neftchi.dto.response;

import java.time.LocalDateTime;

public record NewsOneResponse(
        Long id,
        String headerImage,
        String title,
        String description,
        LocalDateTime date,
        String pdf,
        String video,
        String linkYoutube,
        String category) {
}
