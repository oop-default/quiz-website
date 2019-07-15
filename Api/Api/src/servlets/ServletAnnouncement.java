package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Account;
import models.Announcement;
import parsers.AnnouncementParser;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletAnnouncement")
public class ServletAnnouncement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);

        if(isValid(service,request,response)){
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Announcement announcement = gson.fromJson(reader, Announcement.class);
            System.out.println(announcement.getAnnouncement());
            DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
            try {
                manager.addNewAnnouncement(7,"jikssi",announcement.getAnnouncement());
                throw new SQLException("nwnwn");
            } catch (SQLException e) {
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(service.isAuthenticated()){
            DatabaseManager manager =(DatabaseManager)request.getServletContext().getAttribute("database");
            try {
                ArrayList<Announcement> announcements = manager.getAllAnnouncement();
                Gson gson = new Gson();
                String toString = gson.toJson(announcements);
                System.out.println(toString);
                PrintWriter writer = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                writer.print(toString);
                throw new SQLException("nwnwn");
            } catch (SQLException e) {

            }
        }
    }

    private boolean isValid(AuthenticationService service,HttpServletRequest request,HttpServletResponse response){

        if(!service.isAuthenticated()){
            response.setStatus(401);
            return false;
        }
        if(!service.isAdmin()){
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
