package parsers;

import database.DatabaseManager;
import models.Announcement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnnouncementParser {

    public static void addNewAnnouncement(int id,String author, String announcement, DatabaseManager manager) throws SQLException {
        String query = "insert into announcements(announcement,date,author_id,author)values(?,now(),?,?)";
        PreparedStatement pre=manager.getConnection().prepareStatement(query);
        pre.setString(1,announcement);
        pre.setInt(2,id);
        pre.setString(3,author);
        pre.executeUpdate();
    }

    public static ArrayList<Announcement> getAllAnnouncement(DatabaseManager manager) throws SQLException {
        String query ="select * from announcements order by date desc;";
        PreparedStatement pre=manager.getConnection().prepareStatement(query);
        ResultSet rs = pre.executeQuery();
        return parseResultsetToArray(rs);
    }
    private static  ArrayList<Announcement> parseResultsetToArray(ResultSet rs) throws SQLException {
        ArrayList<Announcement> announcements = new ArrayList<>();
        while(rs.next()){
            Announcement announcement = new Announcement();
            announcement.setAnnouncement(rs.getString("announcement"));
            announcement.setAuthor(rs.getString("author"));
            announcement.setId(rs.getInt("id"));
            announcement.setAuthor_id(rs.getInt("author_id"));
            announcements.add(announcement);
        }
        return announcements;
    }
}
