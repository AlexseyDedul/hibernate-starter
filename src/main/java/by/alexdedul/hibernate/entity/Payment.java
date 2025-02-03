package by.alexdedul.hibernate.entity;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Audited
public class Payment implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @Version
    private Long version;

    @JoinColumn(name = "receiver_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotAudited
    private User receiverId;
}
