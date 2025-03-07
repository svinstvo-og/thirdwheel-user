package thirdwheel.user.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thirdwheel.user.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRoleName(String roleName);

    public Role findByRoleId(Long rId) throws EmptyResultDataAccessException;
}
