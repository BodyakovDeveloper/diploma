package com.koval.diploma.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq_generator")
    @SequenceGenerator(name = "group_id_seq_generator", sequenceName = "group_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "group_name", nullable = false, length = 32)
    private String groupName;

    @Column(name = "email", length = 32)
    private String email;

    @OneToOne
    private Student headOfGroup;

    @OneToMany(mappedBy = "group")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    private Cathedra cathedra;
}