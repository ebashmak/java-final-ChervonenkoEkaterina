package com.example.final_exam_chervonenkoekaterina.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Data
public class ChervonenkoEkaterinaEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ChervonenkoEkaterinaUser student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private ChervonenkoEkaterinaCourse course;

    private LocalDateTime enrollmentDate = LocalDateTime.now();
}