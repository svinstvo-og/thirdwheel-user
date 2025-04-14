package thirdwheel.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.entity.Role;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.entity.UserRoleId;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public void assignRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);

        //System.out.println(user.toString() + " " + role.toString());

        UserRole userRole = new UserRole(user, role);
        userRoleRepository.save(userRole);
    }

    public List<String> getUserRoles(User user) {
        List<String> userRoles = new ArrayList<>();
        for (UserRole u : userRoleRepository.findUserRolesByUser(user)) {
            userRoles.add(u.getRole().getRoleName());
        };
        return userRoles;
    }
}
