package com.koval.diploma.repository;

import com.koval.diploma.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {


    @Query("SELECT g FROM Group g INNER JOIN g.subjects s INNER JOIN s.teachers t WHERE t.id = :teacherId")
    List<Group> findGroupsByTeacherId(@Param("teacherId") Long teacherId);

}
