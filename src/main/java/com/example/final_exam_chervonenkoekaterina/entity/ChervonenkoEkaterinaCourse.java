package com.example.final_exam_chervonenkoekaterina.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class ChervonenkoEkaterinaCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ChervonenkoEkaterinaCategory category;

    private Double price;
}