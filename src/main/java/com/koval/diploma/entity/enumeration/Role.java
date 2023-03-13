package com.koval.diploma.entity.enumeration;

public enum Role {
    USER,
    ADMIN
}

/*@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueName", columnNames = {"name"})
})
@Entity
@Setter
@Getter
@ToString(exclude = {"users"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq_generator")
    @SequenceGenerator(name = "role_id_seq_generator", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();
}
*/