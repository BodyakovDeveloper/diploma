package com.koval.diploma.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.koval.diploma.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<List<Student>> getStudentsByGroup(Long id);
}
