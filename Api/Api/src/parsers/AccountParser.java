package parsers;

import database.DatabaseManager;
import models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountParser {

    public static ArrayList<Account> getAccountsByPatrialMatchName(String name, DatabaseManager manager){
        String query = "select * from accounts where username like '%"+name+"%'";
        ArrayList<Account> accounts = new ArrayList<>();
        ResultSet rs = manager.executeQuery(query);
        try{
            while (rs.next()){
                String username = rs.getString("username");
                int id = rs.getInt("id");
                String firstname = rs.getString("first_name");
                String secondname = rs.getString("last_name");
                String gender = rs.getString("gender");
                Account account = new Account(username,"",firstname,secondname,gender);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getUserId(String username , DatabaseManager manager) throws SQLException {
        String query = "select * from accounts where username =(?)";
        Connection con = manager.getConnection();
        PreparedStatement pre = con.prepareStatement(query);
        pre.setString(1,username);
        ResultSet rs = pre.executeQuery();
        try {
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void removeAccount(String username,DatabaseManager manager) throws SQLException {
        String query = "delete from accounts where username=(?)";
        Connection con = manager.getConnection();
        PreparedStatement pre = con.prepareStatement(query);
        pre.setString(1,username);
        pre.executeUpdate();
    }

    public static boolean isAdmin(String username, DatabaseManager manager) throws SQLException {
        String query = "select * from accounts where username=(?)";
        System.out.println(query);

        Connection con = manager.getConnection();
        PreparedStatement pre = con.prepareStatement(query);
        pre.setString(1,username);
        ResultSet set =pre.executeQuery();
        if(set.next()){
            return set.getBoolean("is_admin");
        }
        return false;
    }
}
