package com.koval.diploma.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Table(name = "teachers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_id_seq_generator")
    @SequenceGenerator(name = "teacher_id_seq_generator", sequenceName = "teacher_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private List<Lesson> lessonEntities;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Subject> subjects = new LinkedHashSet<>();

    @ManyToOne
    private Cathedra cathedra;
}