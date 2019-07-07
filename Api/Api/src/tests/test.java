import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Account;
import models.Quiz;
import org.junit.Test;
import parsers.AccountParser;
import parsers.QuizParser;

import java.util.ArrayList;

public class test {
    @Test
    public void  QuizToJsonTest(){
        DatabaseManager manager = new DatabaseManager();
        Quiz quiz = QuizParser.getQuiz(1,manager);
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
}
