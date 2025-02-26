package thirdwheel.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thirdwheel.user.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
