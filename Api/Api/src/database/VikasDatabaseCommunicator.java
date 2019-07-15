package database;
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

    public static boolean accountExistsWithId(int userId, DatabaseManager manager){
        String query = "select * from accounts where id= "+ userId + ";";
        try {
            ResultSet rs = manager.executeGetQuery(query);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // gets ResultSet of accounts of friends
    public static ResultSet getFriends(int idUser, DatabaseManager manager) throws SQLException {
        String query = "select * from accounts a where a.id in (select f.first_id from friends f " +
                "where f.second_id in (select id from accounts b where b.id = " + idUser + ")" +
                " union select f.second_id from friends f where f.first_id in (select id from accounts b " +
                "where b.id = " + idUser+ "));";
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
        String query = "select a.image, a.name from achievements a, achievings b, accounts c where" +
                " c.id = b.account_id and b.achievement_id = a.id and c.id = " + userId + ";";
        return manager.executeGetQuery(query);
    }

    // select * from accounts;
    public static ResultSet getUserInfo(int id, DatabaseManager manager) throws SQLException {
        String query = "select a.id, a.username, a.first_name, a.last_name, a.image, a.is_admin from accounts a where a.id = " + id + ";";
        return manager.executeGetQuery(query);
    }

}


