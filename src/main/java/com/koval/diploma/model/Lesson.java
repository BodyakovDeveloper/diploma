package com.koval.diploma.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_id_seq_generator")
    @SequenceGenerator(name = "class_id_seq_generator", sequenceName = "class_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "class_teacher_fk"))
    private Teacher teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "class_group_fk"))
    private Group group;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_type_id", foreignKey = @ForeignKey(name = "class_class_type_fk"))
    private ClassType classType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "class_subject_fk"))
    private Subject subject;

    @OneToMany(mappedBy = "lessonEntity")
    private List<Mark> marks = new ArrayList<>();

    @OneToMany(mappedBy = "lessonId")
    private List<Attend> attends = new ArrayList<>();

    @Column(name = "date_of_class")
    private LocalDate dateOfClass;
}