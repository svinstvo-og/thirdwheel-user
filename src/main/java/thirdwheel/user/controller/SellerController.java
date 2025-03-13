package thirdwheel.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserRole;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;
import thirdwheel.user.service.CustomUserDetailsService;
import thirdwheel.user.service.RoleService;
import thirdwheel.user.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/user/sellers")
public class SellerController {
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
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @GetMapping
    public List<UserRole> getAllSellers() {
        return userRoleRepository.findUsersByRoleRoleName("SELLER");
    }
}
