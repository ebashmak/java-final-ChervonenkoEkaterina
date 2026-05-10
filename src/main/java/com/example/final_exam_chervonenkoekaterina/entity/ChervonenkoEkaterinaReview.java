package com.example.final_exam_chervonenkoekaterina.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
public class ChervonenkoEkaterinaReview {
    @Id
    private String id;
    private Long courseId;
    private String userEmail;
    private String comment;
    private int rating;
}
