package servlets;

import models.FriendRequest;
import parsers.ZvikisParser;
import com.google.gson.Gson;
import database.DatabaseManager;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.ZvikisDatabaseCommunicator.*;

@WebServlet(name = "ServletFriendRequest")
public class ServletFriendRequest extends HttpServlet {
    // creates new friend request and inserts it into QUIZ_DB.friend_requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int senderId = service.getUserId();
        int receiverId = Integer.parseInt(request.getParameter("userId"));

        try {
            if (requestAlreadyExists(receiverId, senderId, manager) ||
                    areFriends(receiverId, senderId, manager)) {
                response.setStatus(400); // unable to run query already friends
                return;
            } else {
                try {
                    insertFriendRequest(senderId, receiverId, manager);
                    response.setStatus(200); // ok
                } catch (SQLException e) {
                    response.setStatus(409); // request conflicts with the current state of the server.
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
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int id = service.getUserId();
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            final ResultSet friendRequests = getFriendRequests(id, manager);
            ZvikisParser.friendRequestParseToJsonArray(response.getWriter(), friendRequests);

            response.setStatus(200);
        } catch (SQLException e) {
            response.setStatus(500); // something wrong with SQL server
            e.printStackTrace();

        }

    }

    // accept or deny friend request
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        FriendRequest friendRequest = new Gson().fromJson(request.getReader(), FriendRequest.class);
        friendRequest.setReceiverId(service.getUserId());

        try {
            if (areFriends(friendRequest.getSenderId(), friendRequest.getReceiverId(), manager)) {
                response.setStatus(400); // already friends bad request
                return;
            }
        } catch (SQLException e) {
            response.setStatus(500); // unable to run query
        }

        if (friendRequest.getStatus().equals("accept")) {
            acceptFriendRequest(friendRequest, manager);
            response.setStatus(200); // OK
        } else if (friendRequest.getStatus().equals("deny")) {
            denyFriendRequest(friendRequest, manager);
            response.setStatus(200); // OK
        } else {
            response.setStatus(422); // invalid status it may only be accept or deny
        }

    }
}
