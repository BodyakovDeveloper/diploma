package com.koval.diploma.service;

import com.koval.diploma.model.User;

public interface TeacherService {

    User getByEmail(String email);
}
