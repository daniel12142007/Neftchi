package com.example.neftchi.dto.response;

public record MenuPageResponse(Long id,
                               String title,
                               String description,
                               String about,
                               String aboutDescription,
                               String video) {
}