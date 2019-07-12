package database;


import com.mysql.jdbc.ResultSetMetaData;
import models.SubmittedQuiz;
import responseModels.FriendAchievements;
import responseModels.NotPersonalScoreResponse;
import responseModels.friendsQuizzesResponse;
import responseModels.quizzesResponse;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    static  String account = MyDBInfo.MYSQL_USERNAME;
    static  String password = MyDBInfo.MYSQL_PASSWORD;
    static  String server = MyDBInfo.MYSQL_DATABASE_SERVER;
    static  String database = MyDBInfo.MYSQL_DATABASE_NAME;

    private Connection con;
    private Statement stmt;
    private ResultSetMetaData rsmd;


    public DatabaseManager(){
        rsmd = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection( "jdbc:mysql://" + server+"?useSSL=false", account ,password);
            stmt = con.createStatement();
            stmt.executeQuery("USE " + database);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query){
        try {
            Statement statement = con.createStatement();
            ResultSet rs=statement.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    private void insertNewAchievementFor(int personID,int achievingID,long updateTime){
        String query = "insert into achievings(achievment_id,account_id,date_achieved) values ("+achievingID+
                ","+personID+","+
                "FROM_UNIXTIME("+updateTime+"))";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateAchievingsFor(int personID,long updateTime){
        //getting points from here
        String points = "select coalesce(sum(num_points),0) pt from history \n" +
                        "where account_id = "+personID;
        int point = 0;
        ResultSet rspoints = executeQuery(points);

        try {
            rspoints.next();
            point = rspoints.getInt("pt");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //if point is 0, zero achievings are added
        if(point == 0)return;
        //getting total achievements player can take
        String achievements = "select id,num_points from achievements";
        ResultSet rsachievements = executeQuery(achievements);
        ArrayList<Integer> achievementsArr = new ArrayList<>();
        while(true){
            try {
                if (!rsachievements.next()) break;
                int id = rsachievements.getInt("id");
                int num_points = rsachievements.getInt("num_points");
                if(num_points <= point) {
                    achievementsArr.add(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //if cant returns
        if(achievementsArr.size() == 0)return;
        //older achievings
        String personalAchievings = "select achievment_id from achievings \n" +
                                    "where account_id = "+personID;
        ResultSet rsachievings = executeQuery(personalAchievings);
        ArrayList<Integer> achievingsArr = new ArrayList<>();
        while(true){
            try {
                if (!rsachievings.next()) break;
                int id = rsachievings.getInt("achievment_id");
                achievingsArr.add(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i<achievementsArr.size(); i++){
            if(!achievingsArr.contains(achievementsArr.get(i))){
                insertNewAchievementFor(personID,achievementsArr.get(i),updateTime);
            }
        }

    }
    public void saveQuizResults(SubmittedQuiz sq){
        String query = "insert into history(account_id,quiz_id,num_points,time_spent,date_taken) "+
                        "values("+sq.getPersonID()+","+sq.getQuizID()+","+sq.getPoints()+","+sq.getTimeSpent()+","
                        +"FROM_UNIXTIME("+sq.getDateSubmittedMillis()+"))";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateAchievingsFor(sq.getPersonID(),sq.getDateSubmittedMillis());

    }
    public List<quizzesResponse> getCreatedQuizzes(int id) {
        List<quizzesResponse> quizzes = new ArrayList<>();
//        PreparedStatement stmt;
//        try {
//            stmt = con.prepareStatement("select * from quizzes where author_id = ?");
//            stmt.setInt(1, id);
//
//            rs = stmt.executeQuery();
//            while(rs.next()) {
//                Quiz quiz = new Quiz();
//                quiz.setId(rs.getInt(1));
//                quiz.setTitle(rs.getString(2));
//                quizzes.add(quiz);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        quizzesResponse quiz = new quizzesResponse(1, "Sad");
        quizzes.add(quiz);

        return quizzes;
    }

    public List<quizzesResponse> getTakenQuizzes(int id) {
        List<quizzesResponse> quizzes = new ArrayList<>();
//        PreparedStatement stmt;
//        try {
//            stmt = con.prepareStatement("select * from history where account_id = ?");
//            stmt.setInt(1, id);
//
//            rs = stmt.executeQuery();
//            while(rs.next()) {
//                quizzesResponse quiz = new quizzesResponse(rs.getInt(1), rs.getString(2));
//                quizzes.add(quiz);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        quizzesResponse quiz = new quizzesResponse(1, "Sad");
        quizzes.add(quiz);

        return quizzes;
    }

    public List<friendsQuizzesResponse> getFriendsQuizActivity(int id) {
        List<friendsQuizzesResponse> quizzes = new ArrayList<>();

//        PreparedStatement stmt;
//        try {
//            String sql1 = "select f.id, a.username, q.id, q.title, h.num_points " +
//                    "from history h " +
//                    "join quizzes q " +
//                    "on q.id = h.quiz_id " +
//                    "join friends f " +
//                    "on f.id = h.account_id " +
//                    "join accounts a " +
//                    "on a.id = f.second_id " +
//                    "where h.account_id in " +
//                    "(select second_id from friends where first_id=?) " +
//                    "order by h.date_taken " +
//                    "union" +
//                    "select f.id, a.username, q.id, q.title, h.num_points " +
//                    "join history h " +
//                    "on q.id = h.quiz_id " +
//                    "join friends f " +
//                    "on f.id = h.account_id " +
//                    "join accounts a " +
//                    "on a.id = f.first_id" +
//                    "where h.account_id in " +
//                    "(select first_id from friends where second_id=?) " +
//                    "order by h.date_taken";
//            stmt = con.prepareStatement(sql1);
//            stmt.setInt(1, id);
//
//            rs = stmt.executeQuery();
//            while(rs.next()) {
//                friendsQuizzesResponse quiz = new friendsQuizzesResponse(rs.getInt(1),
//                        rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
//                quizzes.add(quiz);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        friendsQuizzesResponse quiz = new friendsQuizzesResponse(1, "vigaca", 1, "ragaca", 100);
        quizzes.add(quiz);

        return quizzes;
    }

    public Connection getConnection(){
        return con;
    }

}
