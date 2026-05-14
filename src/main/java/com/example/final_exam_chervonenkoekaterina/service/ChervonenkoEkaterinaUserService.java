package com.example.final_exam_chervonenkoekaterina.service;

import com.example.final_exam_chervonenkoekaterina.DTO.ChervonenkoEkaterinaUserDTO;
import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaUser;
import com.example.final_exam_chervonenkoekaterina.repository.ChervonenkoEkaterinaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChervonenkoEkaterinaUserService {

    private final ChervonenkoEkaterinaUserRepository userRepository;

    public List<ChervonenkoEkaterinaUserDTO> getAllUsers() {
        log.info("Fetching all users from ChervonenkoEkaterina database");
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ChervonenkoEkaterinaUserDTO getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        ChervonenkoEkaterinaUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDTO(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public ChervonenkoEkaterinaUserDTO updateUser(Long id, ChervonenkoEkaterinaUserDTO dto) {
        log.info("Updating user with id: {}", id);
        ChervonenkoEkaterinaUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        return convertToDTO(userRepository.save(user));
    }

    private ChervonenkoEkaterinaUserDTO convertToDTO(ChervonenkoEkaterinaUser user) {
        ChervonenkoEkaterinaUserDTO dto = new ChervonenkoEkaterinaUserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }
}