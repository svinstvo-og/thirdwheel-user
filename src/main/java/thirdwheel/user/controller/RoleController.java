package thirdwheel.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import thirdwheel.user.dto.RoleAssighnmentRequest;
import thirdwheel.user.entity.Role;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserPrincipal;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;
import thirdwheel.user.service.RoleService;
import thirdwheel.user.service.UserRoleService;
import thirdwheel.user.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class RoleController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRole(@RequestBody Role role) {
        roleService.createRole(role);
    }

    @GetMapping("/all-roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("roles")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, String> getRoles() {
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPrincipal currentUser = (UserPrincipal) userPrincipal;
        User user = userRepository.findByEmail(currentUser.getUsername());
        Map<Long, String> roles = new HashMap<>();
        for (UserRole userRole : userRoleRepository.findUserRolesByUser(user)) {
            roles.put(userRole.getRole().getRoleId(), userRole.getRole().getRoleName());
        }
        return roles;
    }

    @PostMapping("/assign-role")
    public ResponseEntity<Void> assignRole(@RequestBody RoleAssighnmentRequest roleAssighnmentRequest) {
        if (getRoles().containsValue("ADMIN")) {
            userRoleService.assignRoleToUser(roleAssighnmentRequest.getUId(), roleAssighnmentRequest.getRId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/user-roles")
    public List<Long> getUserRoles(@RequestBody User user) {
        List<Long> roleIds = new ArrayList<>();
        //userRoleRepository.findUserRolesByUser(userRepository.findByuId(user.getUId()));
        for (UserRole userRole : userRoleRepository.findUserRolesByUser(user)) {
            roleIds.add(userRole.getRole().getRoleId());
        }
        return roleIds;
    }
}
