package servlets;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Quiz;
import models.Score;
import parsers.AuthenticationService;
import parsers.QuizParser;
import parsers.ScoreParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGlobalTopScores")
public class ServletGlobalTopScores extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizid = request.getParameter("quizid");
        int quizID = Integer.parseInt(quizid);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");


//        String token = request.getHeader("Authorization");
//        AuthenticationService service = manager.getService(token);
//        if(!service.isAuthenticated()){
//            response.setStatus(401);
//            return;
//        }

        ArrayList<Score> scores =  manager.getGlobalTopScores(quizID);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String JsonScores = gson.toJson(scores,ArrayList.class);
        writer.print(JsonScores);
    }
}
