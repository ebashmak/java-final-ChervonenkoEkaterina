package com.example.final_exam_chervonenkoekaterina.service;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaCategoryDTO;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaCategory;
import com.example.final_exam_chervonenkoekaterina.repository.ChervonenkoEkaterinaCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChervonenkoEkaterinaCategoryService {

    private final ChervonenkoEkaterinaCategoryRepository categoryRepository;

    public ChervonenkoEkaterinaCategoryDTO createCategory(ChervonenkoEkaterinaCategoryDTO dto) {
        log.info("Creating category: {}", dto.getName());

        ChervonenkoEkaterinaCategory category = new ChervonenkoEkaterinaCategory();
        category.setName(dto.getName());

        ChervonenkoEkaterinaCategory saved = categoryRepository.save(category);

        return new ChervonenkoEkaterinaCategoryDTO(saved.getId(), saved.getName());
    }

    public List<ChervonenkoEkaterinaCategoryDTO> getAllCategories() {
        log.info("Fetching all categories");

        List<ChervonenkoEkaterinaCategory> allEntities = categoryRepository.findAll();

        List<ChervonenkoEkaterinaCategoryDTO> allDtos = new ArrayList<>();

        for (ChervonenkoEkaterinaCategory category : allEntities) {
            ChervonenkoEkaterinaCategoryDTO dto = new ChervonenkoEkaterinaCategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            allDtos.add(dto);
        }

        return allDtos;
    }

    public void deleteCategory(Long id) {
        log.info("Deleting category id: {}", id);
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Transactional
    public ChervonenkoEkaterinaCategory update(Long id, String newName) {
        log.info("Updating category with id {}. New name: {}", id, newName);

        ChervonenkoEkaterinaCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        category.setName(newName);

        return categoryRepository.save(category);
    }
}