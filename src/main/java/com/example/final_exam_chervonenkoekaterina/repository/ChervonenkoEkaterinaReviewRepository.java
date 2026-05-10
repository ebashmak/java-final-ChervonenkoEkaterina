package com.example.final_exam_chervonenkoekaterina.repository;

import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChervonenkoEkaterinaReviewRepository extends MongoRepository<ChervonenkoEkaterinaReview, String> {
    List<ChervonenkoEkaterinaReview> findByCourseId(Long courseId);
}