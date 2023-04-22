package com.koval.diploma.service;

import com.koval.diploma.entity.Subject;
import com.koval.diploma.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> getSubjects() {
        return subjectRepository.
    }
}
