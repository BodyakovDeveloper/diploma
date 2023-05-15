package com.koval.diploma.repository;

import com.koval.diploma.model.Subject;
import com.koval.diploma.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s JOIN s.teachers t WHERE t.id = :teacherId")
    List<Subject> findAllByTeacher(Long teacherId);


    @Query("SELECT distinct s FROM Subject s INNER JOIN s.teachers t INNER JOIN s.group g WHERE t.id = :teacherId AND g.id = :groupId")
    List<Subject> findAllByTeacherAndGroup(@Param("teacherId") Long teacherId, @Param("groupId") Long groupId);
}