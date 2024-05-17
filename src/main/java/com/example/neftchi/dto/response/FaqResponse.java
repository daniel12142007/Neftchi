package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.Language;

public record FaqResponse(Long id,
                          String question,
                          String answer,
                          String fileName,
                          Language language) {
}