package com.koval.diploma.service;

import com.koval.diploma.model.Subject;
import com.koval.diploma.model.User;

import java.util.List;

public interface SubjectService {

    List<Subject> getForTeacher(User teacher);

    List<Subject> getForTeacherByGroup(User user, Long groupId);

    Subject getById(Long subjectId);

    Subject save(Subject subject);
}
