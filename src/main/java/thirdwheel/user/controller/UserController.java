package thirdwheel.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import thirdwheel.user.dto.PasswordChangeRequest;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.dto.UserResponse;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserPrincipal;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;
import thirdwheel.user.service.CustomUserDetailsService;
import thirdwheel.user.service.RoleService;
import thirdwheel.user.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
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

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PutMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPrincipal currentUser = (UserPrincipal) userPrincipal;
        User user = userRepository.findByEmail(currentUser.getUsername());
        //System.out.println(passwordChangeRequest.getNewPassword());
        user.setPwdHash(bCryptPasswordEncoder.encode(passwordChangeRequest.getNewPassword()));
        userService.updateUser(user);
    }

    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public UserResponse getUser(@RequestParam Long uId) {
        Optional<User> user = userRepository.findById(uId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return UserResponse.builder()
                .uId(uId)
                .Fname(user.get().getFname())
                .Lname(user.get().getLname())
                .email(user.get().getEmail())
                .build();
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "testik";
    };

    @PostMapping("/testik")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String test2(@RequestBody UserRegistrationRequest userRegistrationRequest) {return "zaza";}

    public void getPrincipal(@RequestParam Long uId) {
        userRepository.findById(uId).ifPresent(user -> {
            UserPrincipal userPrincipal = new UserPrincipal(user);
        });
    }
}
