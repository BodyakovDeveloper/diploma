package com.koval.diploma.service;

import com.koval.diploma.model.User;

import java.util.List;

public interface UserService {

    User getByUsername(String username);

    User save(User user);

    List<User> getAll();
}
