package com.koval.diploma.service.impl;

import com.koval.diploma.exception.SubjectNotFoundException;
import com.koval.diploma.model.Subject;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.SubjectRepository;
import com.koval.diploma.repository.TeacherRepository;
import com.koval.diploma.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<Subject> getSubjectsForTeacher(User user) {
        Teacher teacher = teacherRepository.findByUserUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + user.getUsername() + " not found"));
        return subjectRepository.findAllByTeacher(teacher.getId());
    }

    @Override
    public List<Subject> getSubjectsForTeacherByGroup(User user, Long groupId) {
        Teacher teacher = teacherRepository.findByUserUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + user.getUsername() + " not found"));
        return subjectRepository.findAllByTeacherAndGroup(teacher.getId(), groupId);
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with id:" + subjectId + " not found"));
    }
}