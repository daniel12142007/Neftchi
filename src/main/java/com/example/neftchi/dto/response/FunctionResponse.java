package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.Language;

public record FunctionResponse(Long id,
                               String title,
                               String link,
                               String image,
                               Language language) {
}