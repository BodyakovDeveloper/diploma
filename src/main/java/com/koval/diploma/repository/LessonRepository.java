package com.koval.diploma.repository;

import com.koval.diploma.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l WHERE l.teacher.id = :teacherId")
    List<Lesson> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT l FROM Lesson l WHERE l.group.id = :groupId AND l.subject.id = :subjectId order by l.dateOfClass asc")
    List<Lesson> findByGroupAndSubject(@Param("groupId") Long groupId, @Param("subjectId") Long subjectId);
}