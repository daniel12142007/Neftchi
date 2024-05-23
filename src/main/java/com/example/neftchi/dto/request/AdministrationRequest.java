package com.example.neftchi.dto.request;

import com.example.neftchi.model.enums.Language;
import lombok.Data;

@Data
public class AdministrationRequest {
    private String fullName;
    private String description;
    private Language language;
    private String image;
}