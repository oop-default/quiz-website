package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import parsers.AuthenticationService;
import responseModels.friendsQuizzesResponse;
import responseModels.quizzesResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletFriendsQuizActivity")
public class ServletFriendsQuizActivity extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        int id = service.getUserId();

        List<friendsQuizzesResponse> friendsTakenQuizzes = manager.getFriendsQuizActivity(id);
        PrintWriter writer = response.getWriter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(friendsTakenQuizzes);
        writer.print(output);
    }
}
