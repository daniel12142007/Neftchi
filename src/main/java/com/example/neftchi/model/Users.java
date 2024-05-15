package com.example.neftchi.model;

import com.example.neftchi.model.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String code;
    @Enumerated(EnumType.STRING)
    private Role role;
}