package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Quiz;
import parsers.AuthenticationService;
import parsers.QuizParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletFriendsCreatedQuizes")
public class ServletFriendsCreatedQuizes extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        int userId = service.getUserId();
        System.out.println(userId);
        ArrayList<Quiz> arrayList = manager.friendRecentCreatedQuizes(userId);
        Gson gson = new Gson();
        String answerToJson = gson.toJson(arrayList, ArrayList.class);
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        writer.print(answerToJson);
    }
}
