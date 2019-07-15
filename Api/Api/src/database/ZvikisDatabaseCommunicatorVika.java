package database;

import database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZvikisDatabaseCommunicatorVika {

    public static void acceptFriendRequest(int receiverId, int senderId, DatabaseManager manager) {
        deleteFriendRequest(receiverId, senderId, manager);
        insertIntoFriends(receiverId, senderId, manager);
    }

    public static void denyFriendRequest(int receiverId, int senderId, DatabaseManager manager) {
        deleteFriendRequest(receiverId, senderId, manager);
    }

    public static void insertFriendRequest(int senderId, int receiverId, DatabaseManager manager) throws SQLException {
        String query = "insert into friend_requests " +
                "(status, sender_id, receiver_id, date_sent, is_seen) " +
                "values (\"Pending\", " + senderId + ", "
                + receiverId + ", sysdate(), true);";
        manager.executeUpdateQuery(query);
    }

    public static ResultSet getFriendRequests(int id, DatabaseManager manager) throws SQLException {
        String query = "select * from friend_requests where receiver_id = " + id + " order by date_sent desc;";
        return manager.executeGetQuery(query);
    }

    private static void deleteFriendRequest(int receiverId, int senderId, DatabaseManager manager) {
        String query = "delete from friend_requests where sender_id = " + senderId + " and receiver_id = " + receiverId + ";";
        try {
            manager.executeUpdateQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertIntoFriends(int receiverId, int senderId, DatabaseManager manager) {
        String query = "insert into friends (first_id, second_id) values (" + receiverId + ", " + senderId + ");";
        try {
            manager.executeUpdateQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean areFriends(int receiverId, int senderId, DatabaseManager manager) throws SQLException {
        String query = "select count(*) as rowcount from friends where (first_id = " + senderId + " and second_id = "
                + receiverId + ") or (first_id = " + receiverId + " and second_id = " + senderId + " );";
        ResultSet rs = manager.executeGetQuery(query);
        rs.next();
        return rs.getInt("rowcount") != 0;
    }

    public static boolean requestAlreadyExists(int receiverId, int senderId, DatabaseManager manager) throws SQLException {
        String query = "select count(*) as rowcount from friend_requests where (sender_id = " + senderId + " and receiver_id = " +
                receiverId + ")" + " or (sender_id = " + receiverId + " and receiver_id = " + senderId + " );";
        ResultSet rs = manager.executeGetQuery(query);
        rs.next();
        return rs.getInt("rowcount") != 0;
    }

    public static boolean mySentRequestExists(int senderId, int receiverId, DatabaseManager manager) throws SQLException {
        String query = "select count(*) as rowcount from friend_requests where sender_id = " + senderId + " and receiver_id = " +
                receiverId + ";";
        ResultSet rs = manager.executeGetQuery(query);
        rs.next();
        return rs.getInt("rowcount") != 0;
    }

    public static void sendChallenge(int senderId, int receiverId, int quizId, DatabaseManager manager) throws SQLException {
        String query = "insert into challenges " +
                "(quiz_id, status, sender_id, receiver_id, date_sent, is_seen) " +
                "values" +
                "(" + quizId + ", 'pending', " + senderId + ", " + receiverId + ", sysdate(), false);";
        manager.executeUpdateQuery(query);
    }

    //
    public static ResultSet getChallenges(int userId, DatabaseManager manager) throws SQLException {
        String query = "select * from challenges where receiver_id = " + userId + "and status = \'pending\' order by date_sent desc;";
        return manager.executeGetQuery(query);
    }

    public static void sendNote(int senderId, int receiverId, String text, DatabaseManager manager) throws SQLException {

        String query = "insert into notes (note, sender_id, receiver_id, date_sent, is_seen) " +
                "values " +
                "(\'" + text + "\'," + senderId + ", " + receiverId + ", sysdate(), false);";
        manager.executeUpdateQuery(query);
    }

    public static ResultSet getNotes(int userId, DatabaseManager manager) throws SQLException {
        String query = "select * from notes where receiver_id = " + userId + " and not is_seen order by date_sent desc;";
        return manager.executeGetQuery(query);
    }

    public static void markNotesAsSeen(int senderId, int receiverId, DatabaseManager manager) throws SQLException {
        String query = "update notes " +
                "set is_seen = false " +
                "where receiver_id = " + receiverId + " and sender_id = " + senderId + ";";
        manager.executeUpdateQuery(query);
    }
}