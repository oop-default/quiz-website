package servlets;

import VikasModels.VikasDatabaseCommunicator;
import com.google.gson.stream.JsonWriter;
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

import static parsers.VikaParser.resultSetToJsonArray;

//import static VikasModels.VikasDatabaseCommunicator.*;

@WebServlet(name = "ServletFriends")
public class ServletFriends extends HttpServlet {

    // DELETE A FRIEND, Takes id from account
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if (!service.isAuthenticated()) {
            response.setStatus(401);
            return;
        }
        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");

        String username = request.getParameter("username");

        int otherId = 0;
        try {
            otherId = VikasDatabaseCommunicator.getId(username, manager);
        } catch (SQLException e) {
            response.setStatus(404); // user not found
            e.printStackTrace();
        }

        if (username != null) {
            try {
                System.out.println(otherId + " " + service.getUserId());
                VikasDatabaseCommunicator.deleteFriend(service.getUserId(), otherId, manager);
                response.setStatus(200);
            } catch (SQLException e) {
                response.setStatus(500);
            }
        }

    }

    // DISPLAY FRIENDS, Takes username from account
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if (!service.isAuthenticated()) {
            response.setStatus(401);
            return;
        }

        //Account user = new Gson().fromJson(request.getReader(), Account.class);
        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");
        String username = request.getParameter("username");

        if (username != null) {
            try {
                final ResultSet friendsList = VikasDatabaseCommunicator.getFriends(username, manager);

                JsonWriter jw = new JsonWriter(response.getWriter());
                resultSetToJsonArray(jw, friendsList);
                response.setStatus(200);
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(404);
            }

        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
