package parsers;


import database.DatabaseManager;
import models.Answer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnswerParserTest {

    @Test
    public void getAnswers() {
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Answer> answers=AnswerParser.getAnswers(1,manager);
        assertEquals(2,answers.size());
        assertEquals("True",answers.get(0).getAnswer());
        assertEquals("False",answers.get(1).getAnswer());
    }
}