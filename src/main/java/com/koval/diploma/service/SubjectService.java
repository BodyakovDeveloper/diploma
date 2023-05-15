package com.koval.diploma.service;

import com.koval.diploma.model.Subject;
import com.koval.diploma.model.User;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<Subject> getSubjectsForTeacher(User teacher);

    List<Subject> getSubjectsForTeacherByGroup(User user, Long groupId);

    Subject getSubjectById(Long subjectId);

}
