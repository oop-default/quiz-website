package parsers;

import database.DatabaseManager;
import models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationParser {
    public static boolean validUser(String username,String password, DatabaseManager manager){
        String query = "select * from accounts where username="+"\""+username+"\""+"and password="+"\""+password+"\"";
        ResultSet rs = manager.executeQuery(query);
        return exists(rs);
    }

    public static boolean usernameExists(String username, DatabaseManager manager){
        String query = "select * from accounts where username="+"\""+username+"\"";
        ResultSet rs = manager.executeQuery(query);
        return exists(rs);
    }

    public static void insertAccount(Account account, DatabaseManager manager){
        if(!usernameExists(account.getUsername(),manager)) {
            String query = "insert into accounts(username,first_name,last_name,gender,password,image," +
                    "date_created,is_deleted,is_banned,num_points,is_admin)" +
                    " values(?,?,?,?,?,?,now(),?,?,?,?)";
            PreparedStatement pre;
            Connection con = manager.getConnection();
            try {
                pre = con.prepareStatement(query);
                pre.setString(1, account.getUsername());
                pre.setString(2, account.getFirstname());
                pre.setString(3, account.getSecondname());
                pre.setString(4, account.getGender());
                pre.setString(5, account.getPassword());
                pre.setString(6,"null");
                pre.setBoolean(7, false);
                pre.setBoolean(8, false);
                pre.setDouble(9, 0);
                pre.setBoolean(10, false);
                pre.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean exists(ResultSet rs){
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
