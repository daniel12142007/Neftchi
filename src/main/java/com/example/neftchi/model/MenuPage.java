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
public class MenuPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 20000)
    private String description;
    private String about;
    @Column(length = 20000)
    private String aboutDescription;
    private String video;
    @Enumerated(EnumType.STRING)
    private Language language;
}