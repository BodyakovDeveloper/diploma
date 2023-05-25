package com.koval.diploma.service.impl;

import com.koval.diploma.model.Cathedra;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.CathedraRepository;
import com.koval.diploma.repository.TeacherRepository;
import com.koval.diploma.repository.UserRepository;
import com.koval.diploma.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final CathedraRepository cathedraRepository;
    private final PasswordEncoder passwordEncoder;

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher with email" + email + " not found"));
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getByCathedra(Long cathedraId) {
        return teacherRepository.findByCathedraId(cathedraId);
    }

    @Override
    public void save(User user, Long cathedraId) {
        Cathedra cathedra = cathedraRepository.findById(cathedraId).orElseThrow();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setCathedra(cathedra);
        userRepository.save(user);
        teacherRepository.save(teacher);
    }

    public void delete(Teacher teacher) {
        // todo
        teacherRepository.delete(teacher);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Teacher with username" + username + " not found"));
    }
}