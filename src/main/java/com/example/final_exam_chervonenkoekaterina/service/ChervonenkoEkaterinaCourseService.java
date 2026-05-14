package com.example.final_exam_chervonenkoekaterina.service;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaCourseDTO;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaCourse;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaCategory;
import com.example.final_exam_chervonenkoekaterina.repository.ChervonenkoEkaterinaCourseRepository;
import com.example.final_exam_chervonenkoekaterina.repository.ChervonenkoEkaterinaCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChervonenkoEkaterinaCourseService {

    private final ChervonenkoEkaterinaCourseRepository courseRepository;
    private final ChervonenkoEkaterinaCategoryRepository categoryRepository;

    // создание курса
    @Transactional
    public ChervonenkoEkaterinaCourseDTO createCourse(ChervonenkoEkaterinaCourseDTO dto) {
        log.info("Creating new course: {}", dto.getTitle());

        ChervonenkoEkaterinaCourse course = new ChervonenkoEkaterinaCourse();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            ChervonenkoEkaterinaCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            course.setCategory(category);
        }

        ChervonenkoEkaterinaCourse savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    // получение всех курсов с пагинацией и поиском
    public Page<ChervonenkoEkaterinaCourseDTO> getAllCourses(String title, Pageable pageable) {
        log.info("Fetching courses with title containing: {}", title);

        Page<ChervonenkoEkaterinaCourse> courses;
        if (title != null && !title.isEmpty()) {
            courses = courseRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            courses = courseRepository.findAll(pageable);
        }

        return courses.map(this::convertToDTO);
    }

    // получение одного курса по айди
    public ChervonenkoEkaterinaCourseDTO getCourseById(Long id) {
        ChervonenkoEkaterinaCourse course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return convertToDTO(course);
    }

    private ChervonenkoEkaterinaCourseDTO convertToDTO(ChervonenkoEkaterinaCourse course) {
        ChervonenkoEkaterinaCourseDTO dto = new ChervonenkoEkaterinaCourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        if (course.getCategory() != null) {
            dto.setCategoryId(course.getCategory().getId());
            dto.setCategoryName(course.getCategory().getName());
        }
        return dto;
    }

    @Transactional
    public ChervonenkoEkaterinaCourseDTO updateCourse(Long id, ChervonenkoEkaterinaCourseDTO dto) {
        ChervonenkoEkaterinaCourse course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null) {
            ChervonenkoEkaterinaCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            course.setCategory(category);
        }

        return convertToDTO(courseRepository.save(course));
    }
}