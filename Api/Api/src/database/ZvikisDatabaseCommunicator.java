package database;

import models.FriendRequest;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZvikisDatabaseCommunicator {

    public static void acceptFriendRequest(FriendRequest friendRequest, DatabaseManager manager) {
        deleteFriendRequest(friendRequest.getReceiverId(), friendRequest.getSenderId(), manager);
        insertIntoFriends(friendRequest.getReceiverId(), friendRequest.getSenderId(), manager);
    }

    public static void denyFriendRequest(FriendRequest friendRequest, DatabaseManager manager) {
        deleteFriendRequest(friendRequest.getReceiverId(), friendRequest.getSenderId(), manager);
    }

    public static void insertFriendRequest(int senderId, int recieverId, DatabaseManager manager) throws SQLException {
        String query = "insert into friend_requests " +
                "(status, sender_id, receiver_id, date_sent, is_seen) " +
                "values (\"Pending\", " + senderId + ", "
                + recieverId + ", sysdate(), true);";
        manager.executeUpdateQuery(query);
    }

    public static ResultSet getFriendRequests(int id, DatabaseManager manager) throws SQLException {
        String query = "select sender_id as fromId, " +
                "(select username from accounts acc where acc.id = sender_id) as fromName " +
                "from friend_requests where receiver_id = " + id + " order by date_sent desc;";
        return manager.executeGetQuery(query);
    }

    private static void deleteFriendRequest(int recieverId, int senderId, DatabaseManager manager) {
        String query = "delete from friend_requests where sender_id = " + senderId + " and receiver_id = " + recieverId + ";";
        try {
            manager.executeUpdateQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertIntoFriends(int recieverId, int senderId, DatabaseManager manager) {
        String query = "insert into friends (first_id, second_id) values (" + recieverId + ", " + senderId + ");";
        try {
            manager.executeUpdateQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean areFriends(int recieverId, int senderId, DatabaseManager manager) throws SQLException {
        String query = "select count(*) as rowcount from friends where (first_id = " + senderId + " and second_id = "
                + recieverId + ") or (first_id = " + recieverId + " and second_id = " + senderId + " );";
        ResultSet rs = manager.executeGetQuery(query);
        rs.next();
        return rs.getInt("rowcount") != 0;
    }

    public static boolean requestAlreadyExists(int recieverId, int senderId, DatabaseManager manager) throws SQLException {
        String query = "select count(*) as rowcount from friend_requests where (sender_id = " + senderId + " and receiver_id = " +
                recieverId + ")" + " or (sender_id = " + recieverId + " and receiver_id = " + senderId + " );";
        ResultSet rs = manager.executeGetQuery(query);
        rs.next();
        return rs.getInt("rowcount") != 0;
    }

    public static void sendChallenge(int senderId, int recieverId, int quizId, DatabaseManager manager) throws SQLException {
        String query = "insert into challenges " +
                "(quiz_id, status, sender_id, receiver_id, date_sent, is_seen) " +
                "values" +
                "(" + quizId + ", 'pending', " + senderId + ", " + recieverId + ", sysdate(), false);";
        manager.executeUpdateQuery(query);
    }

    //
    public static ResultSet getChallenges(int userId, DatabaseManager manager) throws SQLException {
        String query = "select sender_id as fromId, " +
                "(select username from accounts acc where acc.id = sender_id) " +
                "as fromName, quiz_id as quizId from challenges " +
                "where receiver_id = "+ userId +" and status = \'pending\' order by date_sent desc;";
        // System.out.println(query);
        return manager.executeGetQuery(query);
    }

    public static void sendNote(int senderId, int recieverId, String text, DatabaseManager manager) throws SQLException {

        String query = "insert into notes (note, sender_id, receiver_id, date_sent, is_seen) " +
                "values " +
                "(\'" + text + "\'," + senderId + ", " + recieverId + ", sysdate(), false);";
        System.out.println("sn>" + query);
        manager.executeUpdateQuery(query);
    }

    public static ResultSet getNotes(int userId, DatabaseManager manager) throws SQLException {
        String query = "select sender_id as fromId, " +
                "(select username from accounts acc where acc.id = sender_id) as fromName," +
                " note from notes where receiver_id = " + userId +" and not is_seen order by date_sent desc;";
        return manager.executeGetQuery(query);
    }

    public static void markNotesAsSeen(int senderId, int recieverId, DatabaseManager manager) throws SQLException {
        String query = "update notes " +
                "set is_seen = true " +
                "where receiver_id = " + senderId + " and sender_id = " + recieverId + ";";
        System.out.println("mnas>" + query);
        manager.executeUpdateQuery(query);
    }

    public static void answerChallenge(int id, int fromId, int quizId, String answer, DatabaseManager manager) throws SQLException {
        String query = "delete from challenges where sender_id = " + fromId + " and receiver_id = " + id + " and quiz_id = " + quizId + ";";
        manager.executeUpdateQuery(query);
    }
}
