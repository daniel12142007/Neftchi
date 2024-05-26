package com.example.neftchi.dto.request;

import lombok.Data;

@Data
public class CategoryRequest {
    private String category;
    private String color;
    private int queueNumber;
}