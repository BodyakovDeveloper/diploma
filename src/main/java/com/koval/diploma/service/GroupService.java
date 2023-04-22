package com.koval.diploma.service;

import com.koval.diploma.entity.Student;
import com.koval.diploma.repository.GroupRepository;
import com.koval.diploma.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public List<Student> getStudentsByGroup(Long groupId) {

        return studentRepository.getStudentsByGroup(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Student with groupId: " + groupId + " not found"));
    }
}
