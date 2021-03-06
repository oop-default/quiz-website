package servlets;

import com.google.gson.stream.JsonWriter;
import database.DatabaseManager;
import database.VikasDatabaseCommunicator;
import parsers.AuthenticationService;
import parsers.VikaParser;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ServletAchievements")
public class ServletAchievements extends HttpServlet {

    // Gets achievements of a user, uses account's id
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(! service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");

        Integer userId = Integer.parseInt(request.getParameter("id"));

        /*
        int idOthers = 0;
        try {
            idOthers = VikasDatabaseCommunicator.getId(username, manager);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(404);
        }
        if (idOthers == 0) {
            response.setStatus(404);
            return;
        }
        */

        if (VikasDatabaseCommunicator.accountExistsWithId(userId, manager)) {
            try {
                final ResultSet achievements = VikasDatabaseCommunicator.getAchievements(userId, manager);
                JsonWriter jw = new JsonWriter(response.getWriter());
                VikaParser.resultSetToJsonArray(jw, achievements);
                response.setStatus(200);
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(500);
            }
        } else {
            response.setStatus(404);
            return;
        }


    }
}

