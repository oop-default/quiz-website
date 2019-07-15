package servlets;

import database.VikasDatabaseCommunicator;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import database.DatabaseManager;
import models.FriendRequestVika;
import parsers.AuthenticationService;
import parsers.VikaParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.ZvikisDatabaseCommunicatorVika.*;

@WebServlet(name = "ServletFriendRequests")
public class ServletFriendRequests extends HttpServlet {

    // send friend request
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(! service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");

        int senderId = service.getUserId();
        Integer receiverId = Integer.parseInt(request.getParameter("id"));

        try {
            if (requestAlreadyExists(receiverId, senderId, manager) ||
                    areFriends(receiverId, senderId, manager)) {
                response.setStatus(007); // already friend
                return;
            } else {
                try {
                    insertFriendRequest(senderId, receiverId, manager);
                    response.setStatus(200); // ok
                    return;
                } catch (SQLException e) {
                    response.setStatus(409); // This response is sent when a request conflicts with the current state of the server.
                }
            }
        } catch (SQLException e) {
            response.setStatus(500); // unable to run query
            e.printStackTrace();
        }
    }

    // get all friend request sent to user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(! service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int id = service.getUserId();
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");

        try {
            final ResultSet friendRequests = getFriendRequests(id, manager);
            JsonWriter jw = new JsonWriter(response.getWriter());
            VikaParser.resultSetToJsonArray(jw, friendRequests);
            response.setStatus(200);
        } catch (SQLException e) {
            response.setStatus(500); // something wrong with SQL server
        }
    }

    // accept or deny friend request
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(! service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        FriendRequestVika frq = gson.fromJson(reader, FriendRequestVika.class);

        Integer otherId= Integer.parseInt(request.getParameter("id"));
        String status = frq.getStatus();

        try {
            System.out.println("other id is " + otherId + ", mine is " + service.getUserId());
            if (areFriends(otherId, service.getUserId(), manager) ||
                    areFriends(service.getUserId(), otherId, manager)) {
                response.setStatus(405); // already friends
                return;
            }
        } catch (SQLException e) {
            response.setStatus(500); // unable to run query
        }


        if (status.equals("accept")) {
            acceptFriendRequest(service.getUserId(), otherId, manager);
            response.setStatus(200); // OK
        } else if (status.equals("deny")) {
            System.out.println("you rock you can do it");
            denyFriendRequest(service.getUserId(), otherId, manager);
            denyFriendRequest(otherId, service.getUserId(), manager);
            response.setStatus(200); // OK
        } else {
            response.setStatus(422); // invalid status it may only be accept or deny
        }

    }
}
