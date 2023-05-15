package com.koval.diploma.repository;

import com.koval.diploma.model.Attend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendRepository extends JpaRepository<Attend, Long> {
}