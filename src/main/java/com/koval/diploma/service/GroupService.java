package com.koval.diploma.service;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;

import java.util.List;

public interface GroupService {

    List<Group> getForTeacher(String currentPrincipalName);

    Group getById(Long groupId);

    Group save(Group group);

    Group update(Group group);

    List<Group> getAll();
}
