package com.example.final_exam_chervonenkoekaterina.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "lessons")
@Data
public class ChervonenkoEkaterinaLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private ChervonenkoEkaterinaCourse course;
}