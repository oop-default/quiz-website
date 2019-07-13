package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.SubmittedQuiz;

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
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        SubmittedQuiz sq = gson.fromJson(reader, SubmittedQuiz.class);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        manager.saveQuizResults(sq);
        System.out.println("asd");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
