package thirdwheel.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.entity.User;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;
import thirdwheel.user.service.RoleService;
import thirdwheel.user.service.UserService;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.findByEmail(userRegistrationRequest.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with such email already exists");
        }
        userRegistrationRequest.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword()));

        userService.createUser(userRegistrationRequest);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void test() {};
}
