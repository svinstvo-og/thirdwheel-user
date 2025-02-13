package thirdwheel.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uId;
    @Column(unique = false, nullable = false)
    private String Fname;
    @Column(unique = false, nullable = false)
    private String Lname;
    @Column(unique = true, nullable = false)
    private String email;
    private String pwdHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
