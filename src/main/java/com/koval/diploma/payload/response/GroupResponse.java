package com.koval.diploma.payload.response;

import com.koval.diploma.model.Student;

import java.util.List;

public record GroupResponse(List<Student> students) {
}