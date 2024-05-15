package com.example.neftchi.model;

import com.example.neftchi.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String headerImage;
    private String video;
    private String pdf;
    private String youtubeLink;
    private LocalDateTime dataCreated;
    @ElementCollection
    private List<String> image;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}