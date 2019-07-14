package servlets;


import VikasModels.VikasDatabaseCommunicator;
import com.google.gson.Gson;
import database.DatabaseManager;
import database.ZvikisDatabaseCommunicator;
import models.UserInfo;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

import static parsers.VikaParser.parseUserInfo;

import static parsers.VikaParser.parseUserInfo;


@WebServlet(name = "ServletAccountInfo")
public class ServletAccountInfo extends HttpServlet {

    // Gets user's info, uses account's id
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }

        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");

        String username = request.getParameter("username");

        try {
            int idOthers = VikasDatabaseCommunicator.getId(username, manager);

            String status = "";

            if (idOthers == 0) {
                response.setStatus(404);
                return;
            }
            if (idOthers == service.getUserId())
                status = "me";
            else if (ZvikisDatabaseCommunicator.areFriends(idOthers, service.getUserId(), manager))
                status = "myFriend";
            else if (manager.haveSentRequest(service.getUserId(), idOthers)) //iRequested
                status = "iRequested";
            else if (manager.haveSentRequest(idOthers, service.getUserId())) //requestedMe
                status = "requestedMe";
            else
                status = "other";


            final ResultSet rs = VikasDatabaseCommunicator.getUserInfo(idOthers, manager);
            UserInfo userInfo = parseUserInfo(rs);
            userInfo.setStatus(status);

            System.out.println(userInfo.toString());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            new Gson().toJson(userInfo, response.getWriter());
            response.setStatus(200);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
