package com.example.neftchi.dto.request;

import com.example.neftchi.model.enums.Language;
import lombok.Data;

@Data
public class PartnersRequest {
    private String title;
    private String category;
    private String link;
    private String image;
    private Language language;
}