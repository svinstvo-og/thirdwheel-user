package thirdwheel.user.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.jdbc.HikariCheckpointRestoreLifecycle;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    String keyEncoded = "";

    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGenerator.generateKey();
        keyEncoded = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(keyEncoded);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
