package Jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class JwtManager {
    public static String createJWS(String username,int id,Boolean isAdmin){

        byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        Key key = new SecretKeySpec(keyBytes,SignatureAlgorithm.HS256.getJcaName());

        String jws = Jwts.builder() // (1)
                .setSubject(username)
                .claim("userId",id)
                .claim("isAdmin",isAdmin)// (2)
                .signWith(key)          // (3)
                .compact();
        return jws;
    }
    public static Jws<Claims> readeJWS(String jwsString){
        Jws<Claims> jws;
        //byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        //Key key = Keys.hmacShaKeyFor(keyBytes);

        byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        Key key = new SecretKeySpec(keyBytes,SignatureAlgorithm.HS256.getJcaName());
        try {
            jws = Jwts.parser()         // (1)
                    .setSigningKey(key)         // (2)
                    .parseClaimsJws(jwsString); // (3)
            return jws;
            // we can safely trust the JWT
        }catch (JwtException ex) {       // (4)
            return null;
        }
    }

}
