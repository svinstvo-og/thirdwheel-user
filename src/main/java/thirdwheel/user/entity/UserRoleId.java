package thirdwheel.user.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}