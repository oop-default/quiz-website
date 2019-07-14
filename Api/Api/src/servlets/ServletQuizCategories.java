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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletQuizCategories")
public class ServletQuizCategories extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        String category = request.getParameter("category");
        System.out.println(category);
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        ArrayList<Quiz> quizzes = null;
        quizzes = manager.getQuizesByCategory(category);
        Gson gson = new Gson();
        String quizzesArray = gson.toJson(quizzes,ArrayList.class);
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        writer.print(quizzesArray);
        writer.flush();
        System.out.println("end");
    }
}
