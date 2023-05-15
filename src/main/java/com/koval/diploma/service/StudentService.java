package com.koval.diploma.service;

import com.koval.diploma.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentByGroup(Long groupId);
}
