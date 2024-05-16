package com.example.neftchi.dto.response;

import com.example.neftchi.model.enums.Language;

public record PartnersResponse(Long id,
                               String title,
                               String category,
                               String link,
                               String image,
                               Language language) {
}