package thirdwheel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.entity.UserRoleId;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findUserRolesByUser(User user);
    List<UserRole> findUsersByRoleRoleName(String roleName);
}
