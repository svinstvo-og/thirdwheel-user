package thirdwheel.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import thirdwheel.user.dto.RoleAssighnmentRequest;
import thirdwheel.user.entity.Role;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;
import thirdwheel.user.service.RoleService;
import thirdwheel.user.service.UserRoleService;
import thirdwheel.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping("/assign-role")
    public void assignRole(@RequestBody RoleAssighnmentRequest roleAssighnmentRequest) {
        System.out.println("Trying to assign role: " + roleAssighnmentRequest.toString());
        userRoleService.assignRoleToUser(roleAssighnmentRequest.getUId(), roleAssighnmentRequest.getRId());
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
