package thirdwheel.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thirdwheel.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRoleName(String roleName);
}
