package servlets;

import parsers.AuthenticationService;
import parsers.VikaParser;
import com.google.gson.stream.JsonWriter;
import database.DatabaseManager;
import database.VikasDatabaseCommunicator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        Integer idOth = Integer.parseInt(request.getParameter("id"));
        int idOthers = idOth.intValue();

        if (VikasDatabaseCommunicator.accountExistsWithId(idOthers, manager)) {
            try {
                System.out.println(idOthers + " " + service.getUserId());
                VikasDatabaseCommunicator.deleteFriend(service.getUserId(), idOthers, manager);
                response.setStatus(200);
            } catch (SQLException e) {
                response.setStatus(500);
            }
        } else {
            response.setStatus(404);
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

        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");

        Integer idOth = Integer.parseInt(request.getParameter("id"));
        int idOthers = idOth.intValue();
        if (VikasDatabaseCommunicator.accountExistsWithId(idOthers, manager)) {
            try {
                final ResultSet friendsList = VikasDatabaseCommunicator.getFriends(idOthers, manager);

                JsonWriter jw = new JsonWriter(response.getWriter());
                VikaParser.resultSetToJsonArray(jw, friendsList);
                response.setStatus(200);
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(404);
            }
        } else
            response.setStatus(404);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}