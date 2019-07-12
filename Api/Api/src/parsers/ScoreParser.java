package parsers;

import database.DatabaseManager;
import models.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScoreParser {
    public static ArrayList<Score> getPersonalTopScores(int userID,int quizID, DatabaseManager manager){
        String query = "select e.num_points from history e " +
                       " where e.account_id = "+userID+" and e.quiz_id = "+ quizID+
                       " order by num_points desc";

        ArrayList<Score> scores = new ArrayList<>();
        ResultSet rs = manager.executeQuery(query);
        try{
            int position = 1;
            while (rs.next()){
                int num = rs.getInt("num_points");
                Score score = new Score(position,"",num);
                scores.add(score);
                position++;
            }
            return scores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Score> getFriendTopScores(int userID,int quizID,DatabaseManager manager){
        String query = "select  fr.username,max(h.num_points) points from " +
                       " (select ac.* from friends f "+
                       " join accounts ac on ac.id = f.second_id "+
                       "  where f.first_id = "+userID+
                       " union "+
                       " select ac.* from friends f "+
                       " join accounts ac on ac.id = f.first_id  "+
                       " where f.second_id = "+userID+" ) fr "+
                       " join history h on h.account_id = fr.id and h.quiz_id = "+quizID+
                       " group by fr.username "+
                       "  order by points desc";

        ArrayList<Score> scores = new ArrayList<>();
        ResultSet rs = manager.executeQuery(query);
        try{
            int position = 1;
            while (rs.next()){
                int num = rs.getInt("points");
                String user = rs.getString("username");
                Score score = new Score(position,user,num);
                scores.add(score);
                position++;
            }
            return scores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Score> getGlobalTopScores(int quizID,DatabaseManager manager){
        String query = "select  acc.username, max(h.num_points) points from accounts acc " +
                " join history h on h.account_id = acc.id and h.quiz_id = "+quizID+
                " group by acc.username "+
                " order by points desc";
        ArrayList<Score> scores = new ArrayList<>();
        ResultSet rs = manager.executeQuery(query);
        try{
            int position = 1;
            while (rs.next()){
                int num = rs.getInt("points");
                String user = rs.getString("username");
                Score score = new Score(position,user,num);
                scores.add(score);
                position++;
            }
            return scores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
