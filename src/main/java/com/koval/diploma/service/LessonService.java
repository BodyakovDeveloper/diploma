package com.koval.diploma.service;

import com.koval.diploma.model.Lesson;

import java.util.List;

public interface LessonService {

    Lesson createLesson(Lesson lesson);

    List<Lesson> getLessonsForTeacher(Long teacherId);

    List<Lesson> getLessonsForTeacherByGroupAndSubject(Long groupId, Long subjectId);
}