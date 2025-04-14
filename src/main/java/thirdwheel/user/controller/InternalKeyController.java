package thirdwheel.user.controller;


//TEMPORARY
//Not safe for deployment, to be fixed once docker will be set up

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thirdwheel.user.service.JwtKeyService;
import thirdwheel.user.service.JwtService;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/internal/jwt/key")
public class InternalKeyController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/encoded")
    public String getJwtSecretKeyEncoded() {
        JwtKeyService jwtKeyService = jwtService.getJwtKeyService();
        return jwtKeyService.getKeyEncoded();
    }

}
