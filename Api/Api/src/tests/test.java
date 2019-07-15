import Jwt.JwtManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import models.Account;
import models.Quiz;
import org.junit.Test;
import parsers.AccountParser;
import parsers.QuizParser;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    @Test
    public void  QuizToJsonTest(){
        DatabaseManager manager = new DatabaseManager();
        Quiz quiz = null;
        try {
            quiz = QuizParser.getQuiz(1,manager);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String quizToJson = gson.toJson(quiz,Quiz.class);
        System.out.println(quizToJson);


    }

    @Test
    public void AccountParseTest(){
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Account> accounts= AccountParser.getAccountsByPatrialMatchName("jik",manager);

        for (Account ac:
             accounts) {
            System.out.println(ac.getUsername());
        }
    }

    @Test

    public void testJWTmanager(){
        String token = JwtManager.createJWS("giorgi",1,true);
        System.out.println(token);

        Jws<Claims> claims = JwtManager.readeJWS(token);

        System.out.println(claims.getBody().getSubject());
        System.out.println(claims.getBody().get("userId"));
        System.out.println(claims.getBody().get("isAdmin"));

    }
}
