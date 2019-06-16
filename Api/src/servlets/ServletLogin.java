package servlets;

import com.google.gson.Gson;
import cracker.PasswordGenerator;
import database.DatabaseManager;
import models.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Account account = gson.fromJson(reader, Account.class);
        DatabaseManager manager = (DatabaseManager) getServletContext().getAttribute("database");
        String hashedPassword = PasswordGenerator.generate(account.getPassword());
        if(manager.validUser(account.getUsername(),hashedPassword)){
            response.getWriter().println("{\"valid\":true}");
            System.out.println("welcome");
        }else{
            response.getWriter().println("{\"valid\":false}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
