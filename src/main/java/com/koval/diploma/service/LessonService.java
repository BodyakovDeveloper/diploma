package com.koval.diploma.service;

import com.koval.diploma.model.Lesson;

import java.util.List;

public interface LessonService {

    Lesson save(Lesson lesson);

    List<Lesson> getForTeacher(Long teacherId);

    List<Lesson> getForTeacherByGroupAndSubject(Long groupId, Long subjectId);
}