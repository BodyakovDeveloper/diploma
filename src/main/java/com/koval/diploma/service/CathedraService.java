package com.koval.diploma.service;

import com.koval.diploma.model.Cathedra;

import java.util.List;
import java.util.Optional;

public interface CathedraService {
    Cathedra getById(Long cathedraId);

    List<Cathedra> getAll();
}
