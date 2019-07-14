package servlets;

import com.google.gson.Gson;
import models.Note;
import parsers.ZvikisParser;
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
import java.util.stream.Collectors;

import static database.ZvikisDatabaseCommunicator.*;

@WebServlet(name = "ServletNotes")
public class ServletNote extends HttpServlet {
    // send note (message)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        Note note = new Gson().fromJson(request.getReader(), Note.class);
        int senderId = service.getUserId();

        try {
            sendNote(senderId, note.getReceiverId(), note.getText(), manager);
            markNotesAsSeen(senderId, note.getReceiverId() ,manager);
            response.setStatus(200); // ok
        } catch (SQLException e) {
            response.setStatus(500); // wrong with MYSQL server
        }
    }

    // get received notes (massages) only unseens
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int userId = service.getUserId();

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            final ResultSet notes = getNotes(userId, manager);
            ZvikisParser.notesToJsonArray(response.getWriter(), notes);

            response.setStatus(200); // OK
        } catch (SQLException e) {
            response.setStatus(500); // something wrong with SQL server
            e.printStackTrace();
        }
    }


}
