package com.koval.diploma.service.impl;

import com.koval.diploma.model.Lesson;
import com.koval.diploma.repository.LessonRepository;
import com.koval.diploma.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getLessonsForTeacher(Long teacherId) {
        return lessonRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<Lesson> getLessonsForTeacherByGroupAndSubject(Long groupId, Long subjectId) {
        return lessonRepository.findByGroupAndSubject(groupId, subjectId);
    }
}
