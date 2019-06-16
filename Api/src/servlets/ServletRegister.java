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

@WebServlet(name = "ServletRegister")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Account account = gson.fromJson(reader, Account.class);
        System.out.println(account.toString());

        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        if(account.getUsername()==null||account.getPassword()==null||account.getFirstname()==null||account.getSecondname()==null||
                account.getGender()==null||account.getEmail()==null){
            response.getWriter().println("{\"valid\":false}");
        }else{
            if(manager.accountExists(account.getUsername())){
                response.getWriter().println("{\"valid\":false}");
            }else if(manager.mailExists(account.getEmail())){
                response.getWriter().println("{\"valid\":false}");
            }else{
                account.setPassword(PasswordGenerator.generate(account.getPassword()));
                manager.insertAccount(account);
                response.getWriter().println("{\"valid\":true}");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Register");
    }
}
