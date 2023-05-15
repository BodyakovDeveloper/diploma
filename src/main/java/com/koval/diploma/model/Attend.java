package com.koval.diploma.model;

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

@Table(name = "attends")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attend {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attend_id_seq_generator")
    @SequenceGenerator(name = "attend_id_seq_generator", sequenceName = "attend_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "attended_at_class")
    private Boolean attendedAtClass;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id", foreignKey = @ForeignKey(name = "attend_class_fk"))
    private Lesson lessonId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "attend_student_fk"))
    private Student student;
}