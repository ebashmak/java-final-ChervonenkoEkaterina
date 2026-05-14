package com.example.final_exam_chervonenkoekaterina.service;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaReviewDTO;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaReview;
import com.example.final_exam_chervonenkoekaterina.repository.ChervonenkoEkaterinaReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChervonenkoEkaterinaReviewService {

    private final ChervonenkoEkaterinaReviewRepository reviewRepository;

    public ChervonenkoEkaterinaReviewDTO addReview(ChervonenkoEkaterinaReviewDTO dto) {
        log.info("Adding new review for course ID: {}", dto.getCourseId());

        ChervonenkoEkaterinaReview review = new ChervonenkoEkaterinaReview();
        review.setCourseId(dto.getCourseId());
        review.setUserEmail(dto.getUserEmail());
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());

        ChervonenkoEkaterinaReview saved = reviewRepository.save(review);
        dto.setId(saved.getId());
        return dto;
    }

    public List<ChervonenkoEkaterinaReviewDTO> getReviewsByCourse(Long courseId) {
        return reviewRepository.findByCourseId(courseId).stream()
                .map(r -> {
                    ChervonenkoEkaterinaReviewDTO dto = new ChervonenkoEkaterinaReviewDTO();
                    dto.setId(r.getId());
                    dto.setCourseId(r.getCourseId());
                    dto.setUserEmail(r.getUserEmail());
                    dto.setComment(r.getComment());
                    dto.setRating(r.getRating());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}