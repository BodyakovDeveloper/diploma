package com.koval.diploma.repository;

import com.koval.diploma.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByUserEmail(String email);
    Optional<Teacher> findByUserUsername(String email);
}
