package parsers;

import database.DatabaseManager;
import models.Answer;
import models.Question;
import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionParser {

    public static ArrayList<Question> getQuestions(int quizzId, DatabaseManager manager){
        String query = "select * from questions where quiz_id="+quizzId;
        ResultSet rs = manager.executeQuery(query);
        ArrayList<Question> questions = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) return questions;
                String type = rs.getString("type");
                String question = rs.getString("question");
                double num_points = rs.getDouble("num_points");
                String image = rs.getString("image");
                int id = rs.getInt("id");
                ArrayList<Answer> answers = AnswerParser.getAnswers(id,manager);
                Question question1 = new Question(type,question,num_points,image,answers);
                questions.add(question1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
