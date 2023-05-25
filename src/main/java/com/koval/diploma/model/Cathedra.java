package com.koval.diploma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@ToString
@Setter
@Getter
public class Cathedra {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cathedra")
    @ToString.Exclude
    List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "cathedra")
    @ToString.Exclude
    List<Teacher> teachers = new ArrayList<>();
}