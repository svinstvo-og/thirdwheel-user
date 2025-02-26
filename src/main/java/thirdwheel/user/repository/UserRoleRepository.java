package thirdwheel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.entity.UserRoleId;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    // Find all roles for a given user ID
    List<UserRole> findUserRolesById(Long uId);
}
