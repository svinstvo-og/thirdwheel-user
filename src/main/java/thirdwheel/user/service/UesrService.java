package thirdwheel.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.dto.UserRegistrationRequest;
import thirdwheel.user.entity.User;
import thirdwheel.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UesrService {
    private final UserRepository userRepository;

    public void createUser(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
    }
}
