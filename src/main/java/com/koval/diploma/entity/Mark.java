package com.koval.diploma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "marks")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_id_seq_generator")
    @SequenceGenerator(name = "mark_id_seq_generator", sequenceName = "mark_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "mark")
    private Double mark;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id", foreignKey = @ForeignKey(name = "marks_classes_fk"))
    private Class classEntity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "marks_students_fk"))
    private Student student;
}