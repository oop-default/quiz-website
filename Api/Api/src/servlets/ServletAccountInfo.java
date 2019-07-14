package VikasServlets;

import VikasModels.AuthenticationService;
import VikasModels.UserInfo;
import VikasModels.VikaParser;
import VikasModels.VikasDatabaseCommunicator;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import database.DatabaseManager;
import models.ZvikisDatabaseCommunicator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


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
            if (idOthers == service.getId())
                status = "me";
            else if (ZvikisDatabaseCommunicator.areFriends(idOthers, service.getId(), manager))
                status = "myFriend";
            else if (ZvikisDatabaseCommunicator.mySentRequestExists(service.getId(), idOthers, manager)) //iRequested
                status = "iRequested";
            else if (ZvikisDatabaseCommunicator.mySentRequestExists(idOthers, service.getId(), manager)) //requestedMe
                status = "requestedMe";
            else
                status = "other";


            final ResultSet rs = VikasDatabaseCommunicator.getUserInfo(idOthers, manager);
            UserInfo userInfo = VikaParser.parseUserInfo(rs);
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
