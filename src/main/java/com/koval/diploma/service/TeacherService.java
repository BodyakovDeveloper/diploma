package com.koval.diploma.service;

import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;

import java.util.List;

public interface TeacherService {

    User getByEmail(String email);

    List<Teacher> getAll();

    List<Teacher> getByCathedra(Long cathedraId);

    void save(User user, Long cathedraId);
}
