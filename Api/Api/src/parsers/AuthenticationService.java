package parsers;

import Jwt.JwtManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class AuthenticationService {
    private Jws<Claims> jws;
    public AuthenticationService(String token){
        jws=null;
        if(token!=null){
            jws = JwtManager.readeJWS(parseToken(token));
        }
    }

    public boolean isAuthenticated(){
        return jws!=null;
    }

    public String getUserName(){
        if(jws!=null){
            return jws.getBody().getSubject();
        }
        return null;
    }
    public boolean isAdmin(){
        if(jws!=null){

            return (boolean)jws.getBody().get("isAdmin");
        }
        return false;
    }
    public int getUserId(){
        if(jws!=null){
            return (int)jws.getBody().get("userId");
        }
        return -1;
    }

    private String parseToken(String token){
        String [] parsed = token.split(" ");
        System.out.println(parsed[1]);
        return parsed[1];
    }



}
