package servlets;

import database.DatabaseManager;
import parsers.AccountParser;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletRemoveAccount")
public class ServletRemoveAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("remove");
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        String username = request.getParameter("username");
        System.out.println("service: "+service.getUserName());
        System.out.println(username);
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        if(service.isAdmin()){
            System.out.println("here");
            manager.removeAccount(username);
            System.out.println("removed");
        }else{
            response.setStatus(403);
        }

    }

}
