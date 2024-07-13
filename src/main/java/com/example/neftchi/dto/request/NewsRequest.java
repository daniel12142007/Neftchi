package com.example.neftchi.dto.request;

import com.example.neftchi.model.enums.Language;

import javax.validation.constraints.NotNull;
import java.util.List;

public record NewsRequest(
        @NotNull
        List<String> images,
        String video,
        String pdf,
        String linkYoutube,
        @NotNull
        String title,
        @NotNull
        Language language,
        @NotNull
        String description
) {
}