package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Score;
import parsers.ScoreParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletPersonalTopScores")
public class ServletPersonalTopScores extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("id");
        String quizid = request.getParameter("quizid");
        int quizID = Integer.parseInt(quizid);
        int id = Integer.parseInt(userid);

        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        ArrayList<Score> scores =  manager.getPersonalTopScores(id,quizID);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String JsonScores = gson.toJson(scores,ArrayList.class);
        writer.print(JsonScores);
    }
}
