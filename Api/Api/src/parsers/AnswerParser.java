package parsers;

import database.DatabaseManager;
import models.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerParser {
    public static ArrayList<Answer> getAnswers(int questionId, DatabaseManager manager) throws SQLException {
        String query = "select * from answers where question_id=(?)";
        PreparedStatement pre;
        Connection con = manager.getConnection();
        pre=con.prepareStatement(query);
        pre.setInt(1,questionId);
        ResultSet rs = pre.executeQuery();
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
