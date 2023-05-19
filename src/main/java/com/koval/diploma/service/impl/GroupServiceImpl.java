package com.koval.diploma.service.impl;

import com.koval.diploma.exception.GroupNotFoundException;
import com.koval.diploma.model.Group;
import com.koval.diploma.model.Student;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.repository.GroupRepository;
import com.koval.diploma.repository.StudentRepository;
import com.koval.diploma.repository.TeacherRepository;
import com.koval.diploma.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public List<Student> getStudentsByGroup(Long groupId) {

        return studentRepository.findStudentByGroupId(groupId);
    }

    @Override
    public List<Group> getGroupsForTeacher(String currentPrincipalName) {
        Teacher teacher = teacherRepository.findByUserUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + currentPrincipalName + " not found"));
        return groupRepository.findGroupsByTeacherId(teacher.getId());
    }

    @Override
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with id: " + groupId + " not found"));
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}