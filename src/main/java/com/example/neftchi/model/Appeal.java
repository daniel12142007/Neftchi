package com.example.neftchi.model;

import com.example.neftchi.model.enums.StatusAppeal;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String briefComplaint;
    private String phoneNumber;
    private String email;
    private String description;
    private String fileName;
    @Lob
    private byte[] data;
    @Enumerated(EnumType.STRING)
    private StatusAppeal status;
    private String appealAnswer;
    private LocalDateTime dataCreated;
}