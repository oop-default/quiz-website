package parsers;

import database.DatabaseManager;
import models.Answer;
import responseModels.FriendAchievements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AchievementsParser {
    public static ArrayList<FriendAchievements> getAchievementsFor(int userID, DatabaseManager manager){
        String query = "select friends.id,friends.username,ach.name from (select acc.* from friends f\n" +
                "\t\t\t\tjoin accounts acc on acc.id = f.first_id\n" +
                "\t\t\t\twhere f.second_id = "+userID+"\n" +
                "\t\t\t\tunion\n" +
                "\t\t\t\tselect acc.* from friends f\n" +
                "\t\t\t\tjoin accounts acc on acc.id = f.second_id\n" +
                "\t\t\t\twhere f.first_id = "+userID+") friends \n" +
                "join achievings achi on achi.account_id = friends.id\n" +
                "join achievements ach on ach.id = achi.achievment_id\n" +
                "group by achi.date_achieved\n" +
                "order by achi.date_achieved desc";
        ResultSet rs = manager.executeQuery(query);
        ArrayList<FriendAchievements> res = new ArrayList<>();

        while(true){
            try {
                if (!rs.next()) break;
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String achievement = rs.getString("name");
                FriendAchievements fa = new FriendAchievements(id,user,achievement);
                res.add(fa);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return res;
    }
}
