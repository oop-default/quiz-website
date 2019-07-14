package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Quiz;
import parsers.AchievementsParser;
import parsers.AuthenticationService;
import parsers.QuizParser;
import responseModels.FriendAchievements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ServletFriendsAchievements")
public class ServletFriendsAchievements extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        ArrayList<FriendAchievements> res = manager.getAchievementsFor(1);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String quizToJson = gson.toJson(res,ArrayList.class);
        writer.print(quizToJson);

    }
}
