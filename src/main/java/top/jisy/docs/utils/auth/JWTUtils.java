import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import top.jisy.docs.pojo.User;

import java.util.Calendar;

public class JWTUtils {

    //get token
    public static String getToken(User u){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7); //7 days to expire

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId",u.getId())
                .withClaim("username",u.getName());

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(u.getPassword()));
        return token;
    }

    // Verify the legitimacy of the token and return the token
    public static DecodedJWT verify(String token){
        String password = "";
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        DecodedJWT verify = build.verify(token);
        return verify;
    }
}
