package com.example.final_exam_chervonenkoekaterina.repository;

import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChervonenkoEkaterinaCourseRepository extends JpaRepository<ChervonenkoEkaterinaCourse, Long> {
    // поиск курсов по названию с пагинацией
    Page<ChervonenkoEkaterinaCourse> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}