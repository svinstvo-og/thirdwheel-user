package thirdwheel.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long uId;
    private String Fname;
    private String Lname;
    private String email;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
