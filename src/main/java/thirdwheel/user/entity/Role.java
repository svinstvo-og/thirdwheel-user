package thirdwheel.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(nullable = false, unique = true)
    private String roleName;
}
