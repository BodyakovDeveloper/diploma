package com.koval.diploma.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "class_types", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueClassType", columnNames = "class_type")
})
@Setter
@Getter
@ToString(exclude = {"classes"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_type_id_seq_generator")
    @SequenceGenerator(name = "class_type_id_seq_generator", sequenceName = "class_type_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "class_type", unique = true, nullable = false)
    private String classType;

    @OneToMany(mappedBy = "classType")
    private List<Class> classes = new ArrayList<>();
}