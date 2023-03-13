package com.koval.diploma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Table(name = "subjects")
@Entity
@Setter
@Getter
@ToString(exclude = {"classEntities"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_id_seq_generator")
    @SequenceGenerator(name = "subject_id_seq_generator", sequenceName = "subject_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "subject_name", nullable = false, length = 32)
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private List<Class> classEntities = new ArrayList<>();
}