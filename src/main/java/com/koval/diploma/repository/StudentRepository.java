package com.koval.diploma.repository;

import com.koval.diploma.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.group.id = :groupId")
    List<Student> findStudentByGroupId(@Param("groupId") Long groupId);
}