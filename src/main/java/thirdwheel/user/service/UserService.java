package thirdwheel.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.entity.User;
import thirdwheel.user.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserRegistrationRequest userRegistrationRequest) {

        System.out.println(userRegistrationRequest.getFname() + " + " + userRegistrationRequest.getLname());

        User user = User.builder().fname(userRegistrationRequest.getFname())
                .email(userRegistrationRequest.getEmail())
                .lname(userRegistrationRequest.getLname())
                .pwdHash(userRegistrationRequest.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }
}