package VikasModels;

import database.DatabaseManager;
import models.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VikasDatabaseCommunicator {

    public static int getId(String username, DatabaseManager manager) throws SQLException {
        String query = "select id from accounts where username = '" + username +  "';";
        ResultSet res = manager.executeGetQuery(query);
        if (res.next() == false) return 0;
        else
            return res.getObject("id", Integer.class);
    }

    // gets ResultSet of accounts of friends
    public static ResultSet getFriends(String username, DatabaseManager manager) throws SQLException {
        String query = "select * from accounts a where a.id in (select f.first_id from friends f " +
                "where f.second_id in (select id from accounts b where b.username = '" + username + "')" +
                " union select f.second_id from friends f where f.first_id in (select id from accounts b " +
                "where b.username = '" + username + "'));";
        return manager.executeGetQuery(query);
    }

    public static void deleteFriend(int iDeleteId, int imDeletedId, DatabaseManager manager) throws SQLException {
        String query = "delete from friends" +
                        " where (first_id =" + iDeleteId + " and second_id =" + imDeletedId + ") or " +
                        "(first_id =" + imDeletedId + " and second_id =" + iDeleteId + ");";
        manager.executeUpdateQuery(query);
    }

    // returns achievements
    public static ResultSet getAchievements(int userId, DatabaseManager manager) throws SQLException {
        String query = "select * from achievements ach, achievings a, accounts ac " +
                        "where ac.id = " + userId + " and ac.id = a.account_id and ach.id = a.achievment_id;";
        return manager.executeGetQuery(query);
    }

    // select * from accounts;
    public static ResultSet getUserInfo(int id, DatabaseManager manager) throws SQLException {
        String query = "select a.username, a.first_name, a.last_name, a.image, a.is_admin from accounts a where a.id = " + id + ";";
        return manager.executeGetQuery(query);
    }

}


