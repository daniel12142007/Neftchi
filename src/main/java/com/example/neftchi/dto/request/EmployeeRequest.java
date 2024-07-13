package com.example.neftchi.dto.request;

public record EmployeeRequest(
        String fullName,
        String image,
        String jobTitle,
        String description
) {
}