package servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import parsers.AuthenticationService;
import responseModels.quizzesResponse;

@WebServlet(name = "ServletCreatedQuizzes")
public class ServletCreatedQuizzes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
        }
        int id = service.getUserId();
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        List<quizzesResponse> createdQuizzes = manager.getCreatedQuizzes(id);
        ServletOutputStream out = response.getOutputStream();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(createdQuizzes);
        out.print(output);
    }
}
