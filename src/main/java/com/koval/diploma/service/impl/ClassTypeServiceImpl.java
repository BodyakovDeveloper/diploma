package com.koval.diploma.service.impl;

import com.koval.diploma.model.ClassType;
import com.koval.diploma.repository.ClassTypeRepository;
import com.koval.diploma.service.ClassTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassTypeServiceImpl implements ClassTypeService {

    private final ClassTypeRepository classTypeRepository;

    @Override
    public List<ClassType> getAllClassTypes() {
        return classTypeRepository.findAll();
    }
}
