package com.koval.diploma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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


@Entity
@ToString(exclude = {"marks", "attends", "groupHead"})
@Table(name = "students")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq_generator")
    @SequenceGenerator(name = "student_id_seq_generator", sequenceName = "student_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "headOfGroup")
    private Group groupHead;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "students_groups_fk"))
    private Group group;

    @OneToMany(mappedBy = "student")
    private List<Mark> marks = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Attend> attends = new ArrayList<>();
}