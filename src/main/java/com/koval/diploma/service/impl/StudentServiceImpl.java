package com.koval.diploma.service.impl;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.StudentRepository;
import com.koval.diploma.repository.UserRepository;
import com.koval.diploma.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<Student> getStudentsByGroup(Long groupId) {
        return studentRepository.findStudentByGroupId(groupId);
    }

    @Override
    public void save(User user, Group studentGroup) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Student student = new Student();
        user.setStudent(null);
        student.setGroup(studentGroup);
        User savedUser = userRepository.save(user);
        student.setUser(savedUser);
        studentRepository.save(student);
    }

    public void delete(Student student) {
        // todo
        studentRepository.delete(student);
    }

    public void update(Student student) {
        studentRepository.delete(student);
    }
}
