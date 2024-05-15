package com.example.neftchi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Administration {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String description;
    private String image;
    private String language;
}