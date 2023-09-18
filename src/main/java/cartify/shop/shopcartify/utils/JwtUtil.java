package cartify.shop.shopcartify.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import static cartify.shop.shopcartify.utils.AppUtil.APP_NAME;

public class JwtUtil {

    public static String generateVerificationToken(String email){
        String token = JWT.create()
                .withClaim("user", email)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));
        return token;
    }


    public static String extractEmailFrom(String token){
        var claim = JWT.decode(token).getClaim("user");
        return (String) claim.asMap().get("user");
    }
}
