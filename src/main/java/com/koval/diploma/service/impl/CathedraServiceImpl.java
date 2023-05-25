package com.koval.diploma.service.impl;

import com.koval.diploma.model.Cathedra;
import com.koval.diploma.repository.CathedraRepository;
import com.koval.diploma.service.CathedraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CathedraServiceImpl implements CathedraService {

    private final CathedraRepository cathedraRepository;

    @Override
    public Cathedra getById(Long cathedraId) {
        return cathedraRepository.findById(cathedraId).orElseThrow();
    }

    public void delete(Cathedra cathedra) {
        cathedraRepository.delete(cathedra);
    }

    public Cathedra update(Cathedra cathedra) {
        return new Cathedra();
    }

    public Cathedra save(Cathedra cathedra) {
        return cathedraRepository.save(cathedra);
    }

    @Override
    public List<Cathedra> getAll() {
        return cathedraRepository.findAll();
    }
}