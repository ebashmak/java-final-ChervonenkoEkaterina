package com.example.final_exam_chervonenkoekaterina.repository;

import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChervonenkoEkaterinaLessonRepository extends JpaRepository<ChervonenkoEkaterinaLesson, Long> {
    List<ChervonenkoEkaterinaLesson> findByCourseId(Long courseId);
}