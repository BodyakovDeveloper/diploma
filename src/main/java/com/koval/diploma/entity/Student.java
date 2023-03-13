package com.koval.diploma.entity;

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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@ToString(exclude = {"marks", "attends"})
@Table(name = "students", uniqueConstraints = {
        @UniqueConstraint(name = "UniquePhoneNumberAndEmail", columnNames = {"email", "phone_number"})
})
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

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "students_groups_fk"))
    private Group group;

    @OneToMany(mappedBy = "student")
    private List<Mark> marks = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Attend> attends = new ArrayList<>();
}