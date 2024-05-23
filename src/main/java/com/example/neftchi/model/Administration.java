package com.example.neftchi.model;

import com.example.neftchi.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private Language language;
}