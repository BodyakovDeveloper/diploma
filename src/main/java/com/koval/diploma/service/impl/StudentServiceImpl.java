package com.koval.diploma.service.impl;

import com.koval.diploma.model.Student;
import com.koval.diploma.repository.StudentRepository;
import com.koval.diploma.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudentByGroup(Long groupId) {
        return studentRepository.findStudentByGroupId(groupId);
    }
}
