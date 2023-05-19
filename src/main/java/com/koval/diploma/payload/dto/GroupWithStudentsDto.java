package com.koval.diploma.payload.dto;

import java.util.List;

public class GroupWithStudentsDto {
    private Long id;
    private String groupName;
    private List<String> studentNames;

    // Constructor
    public GroupWithStudentsDto(Long id, String groupName, List<String> studentNames) {
        this.id = id;
        this.groupName = groupName;
        this.studentNames = studentNames;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
}

