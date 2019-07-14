package parsers;

import database.DatabaseManager;
import models.Question;
import models.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizParser {
    public static Quiz getQuiz(int id, DatabaseManager manager) throws SQLException {
        String query = "select * from quizzes where id=(?)";
        PreparedStatement pre=manager.getConnection().prepareStatement(query);
        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        try {
            if(rs.next()){
                Quiz quiz = new Quiz();
                parseQuiz(rs,quiz,manager);
                ArrayList<Question> questions = QuestionParser.getQuestions(id,manager);
                quiz.setQuestions(questions);
                return quiz;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public  static ArrayList<Quiz> findQuizByTitle(String title,DatabaseManager manager){
        String query = "select * from quizzes where tittle like '%"+title+"%'";
        System.out.println(query);
        ArrayList<Quiz> list= new ArrayList<>();
        ResultSet rs = manager.executeQuery(query);
        try{
            while (rs.next()){
                Quiz quiz = new Quiz();
                parseQuiz(rs,quiz,manager);
                list.add(quiz);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeQuiz(int quizId, DatabaseManager manager){
        String query = "delete from quizzes where id='"+quizId+"'";
        manager.executeUpdateQuery(query);
    }

    public static ArrayList<Quiz> friendRecentCreatedQuizes(int userId,DatabaseManager manager) throws SQLException {
        String query = "select quiz.*,friends.username from (select ac.* from friends f\n" +
                "                       join accounts ac on ac.id = f.second_id \n" +
                "                       where f.first_id = (?)\n" +
                "                       union \n" +
                "                       select ac.* from friends f \n" +
                "                       join accounts ac on ac.id = f.first_id  \n" +
                "                       where f.second_id = (?)) friends\n" +
                "join quizzes quiz on quiz.author_id = friends.id\n" +
                "order by quiz.date_created desc;";
        Connection connection = manager.getConnection();
        ArrayList<Quiz> list= new ArrayList<>();
        PreparedStatement pre;

        pre = connection.prepareStatement(query);
        pre.setInt(1,userId);
        pre.setInt(2,userId);
        ResultSet rs = pre.executeQuery();
        while (rs.next()){
            Quiz quiz = new Quiz();
            parseQuiz(rs,quiz,manager);
            list.add(quiz);
        }
        return list;
    }

    public static ArrayList<Quiz> getQuizesByCategory(String category , DatabaseManager manager) throws SQLException {
        String query = "select * from quizzes q join categories c  where q.category_id = c.id and c.category=(?)";
        PreparedStatement pre;
        manager.getConnection();
        ArrayList<Quiz> quizzes = new ArrayList<>();
        pre = manager.getConnection().prepareStatement(query);
        pre.setString(1,category);
        ResultSet rs = pre.executeQuery();
        while(rs.next()){
            Quiz quiz = new Quiz();
            parseQuiz(rs,quiz,manager);
            quizzes.add(quiz);
        }
        return quizzes;
    }

    private static void parseQuiz(ResultSet  rs,Quiz quiz,DatabaseManager manager) throws SQLException {
        quiz.setId(rs.getInt("id"));
        quiz.setDateCreated(rs.getDate("date_created").toString());
        quiz.setDescription(rs.getString("description"));
        quiz.setNum_points(rs.getInt("num_points"));
        quiz.setTitle(rs.getString("tittle"));
        int categoryId = rs.getInt("category_id");
        int author_id = rs.getInt("author_id");
        String category = getQuizCategory(categoryId,manager);
        String username = getUsername(author_id,manager);
        quiz.setType(category);
        quiz.setAuthor(username);
    }

    private static String getQuizCategory(int categorycId,DatabaseManager manager){
        String query = "select * from categories where id = "+categorycId;
        return find("category",manager,query);
    }

    private static String getUsername(int userId,DatabaseManager manager){
        String query = "select * from accounts where id = "+userId;
        return find("username",manager,query);
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

    public static void removeQuizHistory(int quizId,DatabaseManager manager ) throws SQLException {
        String query = "delete from history where quiz_id=(?)";
        PreparedStatement pre = manager.getConnection().prepareStatement(query);
        pre.setInt(1,quizId);
        pre.executeUpdate();

    }
}
