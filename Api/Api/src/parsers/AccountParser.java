package parsers;

import database.DatabaseManager;
import models.Account;

import javax.xml.crypto.Data;
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
}
