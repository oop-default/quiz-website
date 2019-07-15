package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import database.VikasDatabaseCommunicator;
import database.ZvikisDatabaseCommunicator;
import database.ZvikisDatabaseCommunicatorVika;
import models.UserInfo;
import parsers.AuthenticationService;
import parsers.VikaParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.sql.ResultSet;


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
        String stringid = request.getParameter("id");
        int idOthers;
        if(stringid==null){
            idOthers=service.getUserId();
        }else{
            idOthers=Integer.parseInt(stringid);
        }

        System.out.println("this is id"+idOthers);
        System.out.println("Vart " + service.getUserId() + "-s accountit");

        if (VikasDatabaseCommunicator.accountExistsWithId(idOthers, manager)) {

            try {
                String status = "";

                if (idOthers == 0) {
                    response.setStatus(404);
                    return;
                }
                if (idOthers == service.getUserId())
                    status = "me";
                else if (ZvikisDatabaseCommunicator.areFriends(idOthers, service.getUserId(), manager))
                    status = "myFriend";
                else if (ZvikisDatabaseCommunicatorVika.mySentRequestExists(service.getUserId(), idOthers, manager)) //iRequested
                    status = "iRequested";
                else if (ZvikisDatabaseCommunicatorVika.mySentRequestExists(idOthers, service.getUserId(), manager)) //requestedMe
                    status = "requestedMe";
                else
                    status = "other";


                final ResultSet rs = VikasDatabaseCommunicator.getUserInfo(idOthers, manager);
                UserInfo userInfo = VikaParser.parseUserInfo(rs);
                userInfo.setStatus(status);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                new Gson().toJson(userInfo, response.getWriter());
                response.setStatus(200);

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(500);
            }
        } else {
            response.setStatus(404);
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
