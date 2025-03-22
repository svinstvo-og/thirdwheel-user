package thirdwheel.user.controller;


import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import thirdwheel.user.dto.RefreshJwtRequest;
import thirdwheel.user.dto.UserLoginRequest;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.dto.UserResponse;
import thirdwheel.user.entity.User;
import thirdwheel.user.entity.UserPrincipal;
import thirdwheel.user.repository.RoleRepository;
import thirdwheel.user.repository.UserRepository;
import thirdwheel.user.repository.UserRoleRepository;
import thirdwheel.user.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/user")
public class AuthController {
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

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, String> login(@RequestBody UserLoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();
        response.put("Bearer", userService.verify(loginRequest));
        response.put("Refresh", refreshTokenService.generateToken(loginRequest.getEmail()));
        return response;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        if (userRepository.findByEmail(userRegistrationRequest.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account with such email already exists");
        }
        userRegistrationRequest.setPassword(bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword()));

        userService.createUser(userRegistrationRequest);
    }

    @GetMapping("current")
    public String getAuthenticatedUser(){
        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPrincipal currentUser = (UserPrincipal) userPrincipal;
        User user = userRepository.findByEmail(currentUser.getUsername());
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUId(user.getUId());
        userResponse.setFname(user.getFname());
        userResponse.setLname(user.getLname());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setCreatedAt(user.getCreatedAt());
        return userResponse.toString();
    }

    @GetMapping("refresh-token")
    public String refreshToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        UserDetails userDetails = applicationContext.getBean(CustomUserDetailsService.class).loadUserByUsername(refreshJwtRequest.getEmail());

        if (refreshTokenService.validateToken(refreshJwtRequest.getRefreshToken(), userDetails)) {
            return jwtService.generateToken(refreshJwtRequest.getEmail());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }
    }
}
