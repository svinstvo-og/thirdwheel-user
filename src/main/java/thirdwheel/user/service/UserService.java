package thirdwheel.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.entity.User;
import thirdwheel.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserRegistrationRequest userRegistrationRequest) {

        User user = User.builder().Fname(userRegistrationRequest.getFname())
                .email(userRegistrationRequest.getEmail())
                .Lname(userRegistrationRequest.getLname())
                .pwdHash(userRegistrationRequest.getPassword())
                .build();

        userRepository.save(user);
    }
}