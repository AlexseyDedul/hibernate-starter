package by.alexdedul.hibernate.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@Builder
@Entity
@ToString(exclude = "users")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
