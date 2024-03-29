package com.koval.diploma.payload.dto;

import java.util.List;

public class GroupWithLessonsDto {
    private Long id;
    private String groupName;
    private List<String> lessonSubjects;

    // Constructor
    public GroupWithLessonsDto(Long id, String groupName, List<String> lessonSubjects) {
        this.id = id;
        this.groupName = groupName;
        this.lessonSubjects = lessonSubjects;
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

    public List<String> getLessonSubjects() {
        return lessonSubjects;
    }

    public void setLessonSubjects(List<String> lessonSubjects) {
        this.lessonSubjects = lessonSubjects;
    }
}

