package thirdwheel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thirdwheel.user.entity.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    public UserRole findByUserId(Long userId);
    public UserRole findByRoleId(Long roleId);
}
