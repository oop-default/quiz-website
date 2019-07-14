package parsers;

import database.DatabaseManager;
import models.Question;
import models.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class QuizParser {
    public static Quiz getQuiz(int id, DatabaseManager manager){
        String query = "select * from quizzes where id="+id;
        ResultSet rs = manager.executeQuery(query);
        try {
            if(rs.next()){
                int quizId = rs.getInt("id");
                String title = rs.getString("title");
                int categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                int author_id = rs.getInt("author_id");
                int num_points = rs.getInt("num_points");
                Date date = rs.getDate("date_created");
                String category = getQuizCategory(categoryId,manager);
                String username = getUsername(author_id,manager);
                ArrayList<Question> questions = QuestionParser.getQuestions(id,manager);
                Quiz quiz = new Quiz(quizId,title,category,description,username,num_points,questions,date.toString());

                return quiz;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getQuizCategory(int categorycId,DatabaseManager manager){
        String query = "select * from categories where id = "+categorycId;
        return find("category",manager,query);
    }

    private static String getUsername(int userId,DatabaseManager manager){
        String query = "select * from accounts where id = "+userId;
        return find("username",manager,query);
    }

    public  static ArrayList<Quiz> findQuizByTitle(String title,DatabaseManager manager){
        String query = "select * from quizzes where title like '%"+title+"%'";
        System.out.println(query);
        ArrayList<Quiz> list= new ArrayList<>();
        ResultSet rs = manager.executeQuery(query);
        try{
            while (rs.next()){
                int id = rs.getInt("id");
                String title1 = rs.getString("title");
                int categoryId = rs.getInt("category_id");
                String description = rs.getString("description");
                int author_id = rs.getInt("author_id");
                int num_points = rs.getInt("num_points");
                Date date = rs.getDate("date_created");
                String category = getQuizCategory(categoryId,manager);
                String username = getUsername(author_id,manager);
                Quiz quiz = new Quiz(id,title1,category,description,username,num_points,null,date.toString());
                list.add(quiz);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String find(String name,DatabaseManager manager,String query){
        ResultSet rs = manager.executeQuery(query);
        try {
            if(rs.next()){
                return rs.getString(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
