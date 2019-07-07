package parsers;

import database.DatabaseManager;
import models.Question;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuestionParserTest {

    @Test
    public void getQuestions() {
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Question> questions=QuestionParser.getQuestions(3,manager);
        assertEquals(3,questions.size());
        assertTrue(questions.get(0).getQuestion().equals("World War 2 started on the 1 September 1939"));
        assertTrue(questions.get(1).getQuestion().equals("The attack on Pearl Harbour brought the US into the war"));
        assertTrue(questions.get(2).getQuestion().equals("World War 2 ended in the year 1946"));
    }
}