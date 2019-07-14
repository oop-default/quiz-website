package Jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtManager {
    public static String createJWS(String username,int id,Boolean isAdmin){

        byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        Key key = Keys.hmacShaKeyFor(keyBytes);
        String jws = Jwts.builder() // (1)
                .setSubject(username)
                .claim("userId",id)
                .claim("isAdmin",isAdmin)// (2)
                .signWith(key,SignatureAlgorithm.HS256)          // (3)
                .compact();
        return jws;
    }
    public static Jws<Claims> readeJWS(String jwsString){
        Jws<Claims> jws;
        byte [] keyBytes = SecurityConstants.JWT_SECRET.getBytes();
        Key key = Keys.hmacShaKeyFor(keyBytes);
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
