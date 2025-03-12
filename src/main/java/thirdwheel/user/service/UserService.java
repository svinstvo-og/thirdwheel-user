package thirdwheel.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.entity.Role;
import thirdwheel.user.entity.User;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public void createUser(UserRegistrationRequest userRegistrationRequest) {

        System.out.println(userRegistrationRequest.getFname() + " - " + userRegistrationRequest.getLname());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName("USER");

        if (userRole == null) {
            Role role = new Role();
            role.setRoleName("USER");
            roleRepository.save(role);
        }
        roles.add(userRole);

        User user = User.builder().fname(userRegistrationRequest.getFname())
                .email(userRegistrationRequest.getEmail())
                .lname(userRegistrationRequest.getLname())
                .pwdHash(userRegistrationRequest.getPassword())
                .createdAt(LocalDateTime.now())
                .roles(roles)
                .build();

        userRepository.save(user);
    }

    public void updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}