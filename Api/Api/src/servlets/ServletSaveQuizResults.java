package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.SubmittedQuiz;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ServletSaveQuizResults")
public class ServletSaveQuizResults extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        SubmittedQuiz sq = gson.fromJson(reader, SubmittedQuiz.class);
        System.out.println(sq.getPersonID());
        sq.setPersonID(service.getUserId());
        System.out.println("servise: "+service.getUserId());
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        manager.saveQuizResults(sq);
    }
}
