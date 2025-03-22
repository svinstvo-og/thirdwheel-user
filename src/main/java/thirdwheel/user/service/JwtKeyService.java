package thirdwheel.user.service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Getter
@Service
public class JwtKeyService {

    private String keyEncoded = "";

    JwtKeyService() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGenerator.generateKey();
        keyEncoded = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.keyEncoded);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
