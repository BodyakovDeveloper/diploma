package com.koval.diploma.service.impl;

import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.TeacherRepository;
import com.koval.diploma.repository.UserRepository;
import com.koval.diploma.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher with email" + email + " not found"));
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher with username" + username + " not found"));
    }
}