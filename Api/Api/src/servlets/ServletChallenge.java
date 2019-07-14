package servlets;

import models.Challenge;
import models.ChallengeAnswer;
import parsers.ZvikisParser;
import com.google.gson.Gson;
import database.DatabaseManager;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.ZvikisDatabaseCommunicator.*;

@WebServlet(name = "ServletChallenge")
public class ServletChallenge extends HttpServlet {
    // send a challenge from sender to receiver
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int senderId = service.getId();
        Challenge challenge = new Gson().fromJson(request.getReader(), Challenge.class);

        // you can send challenges only to friends
        try {
            if (! areFriends(senderId, challenge.getReceiverId(), manager)) {
                response.setStatus(400);
                return;
            }
            sendChallenge(senderId, challenge.getReceiverId(), challenge.getQuizId(), manager);
        } catch (SQLException e) {
            response.setStatus(500);
            e.printStackTrace();
        }
        response.setStatus(200);
    }

    // get challenges sent to user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int id = service.getId();

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            final ResultSet challenges = getChallenges(id, manager);
            ZvikisParser.challengesToJsonArray(response.getWriter(), challenges);

            response.setStatus(200);
        } catch (SQLException e) {
            response.setStatus(500); // something wrong with SQL server
            e.printStackTrace();

        }
    }

    // accept or deny challenge
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        AuthenticationService service = manager.getService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        int id = service.getId();
        ChallengeAnswer challenge = new Gson().fromJson(request.getReader(), ChallengeAnswer.class);

        try {
            answerChallenge(id, challenge.getFromId(), challenge.getQuizId(),challenge.getAnswer(), manager);
        } catch (SQLException e) {
            response.setStatus(500);
            e.printStackTrace();
        }
        response.setStatus(200);

    }
}
