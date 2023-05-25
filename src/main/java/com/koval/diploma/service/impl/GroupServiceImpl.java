package com.koval.diploma.service.impl;

import com.koval.diploma.exception.GroupNotFoundException;
import com.koval.diploma.model.Group;
import com.koval.diploma.model.Teacher;
import com.koval.diploma.model.User;
import com.koval.diploma.repository.GroupRepository;
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
    private final TeacherRepository teacherRepository;

    @Override
    public List<Group> getForTeacher(String currentPrincipalName) {
        Teacher teacher = teacherRepository.findByUserUsername(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + currentPrincipalName + " not found"));
        return groupRepository.findGroupsByTeacherId(teacher.getId());
    }

    @Override
    public Group getById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group with id: " + groupId + " not found"));
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public Group update(Group group) {
        Group toSave = groupRepository.findById(group.getId()).orElseThrow();
        group.setHeadOfGroup(toSave.getHeadOfGroup());
        group.setEmail(toSave.getEmail());
        return group;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }
}