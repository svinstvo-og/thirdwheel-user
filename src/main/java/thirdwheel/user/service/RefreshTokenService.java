package thirdwheel.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private JwtKeyService jwtKeyService;

    @Autowired
    public RefreshTokenService(JwtKeyService jwtKeyService) {
        this.jwtKeyService = jwtKeyService;
    }




}
