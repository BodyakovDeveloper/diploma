package com.koval.diploma.service;

import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;

import java.util.List;

public interface GroupService {

    List<Student> getStudentsByGroup(Long groupId);

    List<Group> getGroupsForTeacher(String currentPrincipalName);

    Group getGroupById(Long groupId);

    Group save(Group group);

    List<Group> getAll();
}
