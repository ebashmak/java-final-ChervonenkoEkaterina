package com.example.final_exam_chervonenkoekaterina.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class ChervonenkoEkaterinaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
}