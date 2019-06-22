package Jwt;



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtManager {
    public static String createJWS(){
        byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        Key key = Keys.hmacShaKeyFor(keyBytes);
        String jws = Jwts.builder() // (1)

                .setSubject("Bob")      // (2)

                .signWith(SignatureAlgorithm.HS256,key)          // (3)

                .compact();
        return jws;
    }
    public static String readeJWS(String jwsString){
        Jws<Claims> jws;
        byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        Key key = Keys.hmacShaKeyFor(keyBytes);
        try {
            jws = Jwts.parser()         // (1)
                    .setSigningKey(key)         // (2)
                    .parseClaimsJws(jwsString); // (3)
            return jws.getBody().getSubject();
            // we can safely trust the JWT
        }catch (JwtException ex) {       // (4)

                // we *cannot* use the JWT as intended by its creator
            }
        return null;
    }

}
