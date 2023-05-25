package com.koval.diploma.service.impl;

import com.koval.diploma.model.Cathedra;
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
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    public Lesson update(Lesson cathedra) {
        return new Lesson();
    }

    @Override
    public List<Lesson> getForTeacher(Long teacherId) {
        return lessonRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<Lesson> getForTeacherByGroupAndSubject(Long groupId, Long subjectId) {
        return lessonRepository.findByGroupAndSubject(groupId, subjectId);
    }
}
