package com.example.final_exam_chervonenkoekaterina.repository;

import com.example.final_exam_chervonenkoekaterina.entity.ChervonenkoEkaterinaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChervonenkoEkaterinaUserRepository extends JpaRepository<ChervonenkoEkaterinaUser, Long> {
    Optional<ChervonenkoEkaterinaUser> findByEmail(String email);
    boolean existsByEmail(String email);
}