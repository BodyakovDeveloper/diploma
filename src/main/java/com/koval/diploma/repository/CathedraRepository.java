package com.koval.diploma.repository;

import com.koval.diploma.model.Cathedra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CathedraRepository extends JpaRepository<Cathedra, Long> {
    List<Cathedra> findByOrderById();
}
