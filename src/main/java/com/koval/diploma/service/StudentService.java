package com.koval.diploma.service;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.User;

import java.util.List;

public interface StudentService {
    List<Student> getStudentsByGroup(Long groupId);

    void save(User user, Group studentGroup);
}
