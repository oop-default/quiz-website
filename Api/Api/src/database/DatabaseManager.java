package database;


import com.mysql.jdbc.ResultSetMetaData;
import models.Account;

import java.sql.*;

public class DatabaseManager {
    static  String account = MyDBInfo.MYSQL_USERNAME;
    static  String password = MyDBInfo.MYSQL_PASSWORD;
    static  String server = MyDBInfo.MYSQL_DATABASE_SERVER;
    static  String database = MyDBInfo.MYSQL_DATABASE_NAME;

    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;


    public DatabaseManager(){
        rs = null;
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

    public boolean validUser(String username,String password){
        String query = "select * from accounts where username="+"\""+username+"\""+"and password="+"\""+password+"\"";
        System.out.println(query);
        try {
            rs=stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertAccount(Account account){

        String query = "insert into accounts(username,first_name,last_name,mail,gender,password,image," +
                "date_created,is_deleted,is_banned,num_points,is_admin)" +
                " values(?,?,?,?,?,?,?,now(),?,?,?,?)";
        PreparedStatement pre;
        try {
            pre = con.prepareStatement(query);
            pre.setString(1,account.getUsername());
            pre.setString(2,account.getFirstname());
            pre.setString(3,account.getSecondname());
            pre.setString(4,account.getEmail());
            pre.setString(5,account.getGender());
            pre.setString(6,account.getPassword());
            pre.setString(7,"null");
            pre.setBoolean(8,false);
            pre.setBoolean(9,false);
            pre.setDouble(10,0);
            pre.setBoolean(11,false);
            System.out.println(pre.toString());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean accountExists(String username){
        String query = "select * from accounts where username="+"\""+username+"\"";
        System.out.println(query);
        try {
            rs=stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean mailExists(String mail){
        String query = "select * from accounts where mail="+"\""+mail+"\"";
        System.out.println(query);
        try {
            rs=stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
