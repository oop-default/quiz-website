package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import parsers.AuthenticationService;
import responseModels.quizzesResponse;

@WebServlet(name = "ServletCreatedQuizzes")
public class ServletCreatedQuizzes extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        System.out.println(token);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
//        AuthenticationService service = manager.getService(token);
//        if(!service.isAuthenticated()){
//            response.setStatus(401);
//            return;
//        }
//        int id = service.getUserId();
        String userId = request.getParameter("id");
        int id = Integer.parseInt(userId);
        List<quizzesResponse> createdQuizzes = manager.getCreatedQuizzes(id);

        PrintWriter writer = response.getWriter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(createdQuizzes);
        writer.print(output);
    }
}
