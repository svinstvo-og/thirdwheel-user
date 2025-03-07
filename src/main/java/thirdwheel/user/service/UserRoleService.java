package thirdwheel.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.entity.Role;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.entity.UserRoleId;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRoleRepository;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public void assignRoleToUser(Long userId, Long roleId) {
        UserRoleId userRoleId = new UserRoleId(userId, roleId);
    }
}
