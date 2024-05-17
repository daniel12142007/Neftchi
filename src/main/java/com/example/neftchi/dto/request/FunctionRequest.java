package com.example.neftchi.dto.request;

import com.example.neftchi.model.enums.Language;
import lombok.Data;

@Data
public class FunctionRequest {
    private String title;
    private String image;
    private String link;
    private Language language;
}