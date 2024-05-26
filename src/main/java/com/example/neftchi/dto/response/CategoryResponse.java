package com.example.neftchi.dto.response;

public record CategoryResponse(Long id,
                               String category,
                               String color,
                               int queueNumber) {
}