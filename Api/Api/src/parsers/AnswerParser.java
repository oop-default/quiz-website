package parsers;

import database.DatabaseManager;
import models.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerParser {
    public static ArrayList<Answer> getAnswers(int questionId, DatabaseManager manager){
        String query = "select * from answers where question_id="+questionId;
        ResultSet rs = manager.executeQuery(query);
        ArrayList<Answer> answers = new ArrayList<>();
        while(true){
            try {
                if (!rs.next()) break;
                Answer answer = new Answer(rs.getString("answer"),rs.getBoolean("is_correct"));
                answers.add(answer);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return answers;
    }
}
