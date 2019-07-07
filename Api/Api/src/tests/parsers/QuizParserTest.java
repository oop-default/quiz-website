package parsers;

import database.DatabaseManager;
import models.Quiz;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuizParserTest {

    @Test
    public void getQuiz() {
        DatabaseManager manager = new DatabaseManager();
        Quiz quiz = QuizParser.getQuiz(1,manager);
        assertEquals("ziki",quiz.getAuthor());
        assertEquals("Quiz is about capital cities of countries, check how well you know them",quiz.getDescription());
        assertEquals(50,quiz.getNum_points());
        assertEquals("Capital cities",quiz.getTitle());
    }

    @Test
    public void getQuizCategory() {
        DatabaseManager manager = new DatabaseManager();
        String category =QuizParser.getQuizCategory(2,manager);
        assertEquals("Math",category);
    }
    @Test
    public void findQuizByTitleTest(){
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Quiz> quizes = QuizParser.findQuizByTitle("si",manager);
        System.out.println(quizes.size());
        for (Quiz q:
             quizes) {
            System.out.println(q.getTitle());
            System.out.println(q.getDescription());
            System.out.println(q.getType());
        }
    }
}