package com.example.final_exam_chervonenkoekaterina.repository;

import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChervonenkoEkaterinaEnrollmentRepository extends JpaRepository<ChervonenkoEkaterinaEnrollment, Long> {
    List<ChervonenkoEkaterinaEnrollment> findByStudentId(Long studentId);
}