package com.koval.diploma.repository;

import com.koval.diploma.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long>  {
}
