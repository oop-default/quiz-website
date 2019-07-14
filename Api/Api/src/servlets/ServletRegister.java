package servlets;

import Jwt.JwtManager;
import com.google.gson.Gson;
import cracker.PasswordGenerator;
import database.DatabaseManager;
import models.Account;
import parsers.AccountParser;
import parsers.AuthorizationParser;
import responseModels.LoginRegisterResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ServletRegister")
public class ServletRegister extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Account account = gson.fromJson(reader, Account.class);
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        if(account.getUsername().equals("")||account.getPassword().equals("")||
                account.getFirstname().equals("")||account.getSecondname().equals("")||
                account.getGender().equals("")){
            String message = "Incorrect input!";
            LoginRegisterResponse loginRegisterResponse = new LoginRegisterResponse(400,null,message);
            sendResponse(response,gson,loginRegisterResponse);
        }else{
            if(manager.usernameExists(account.getUsername())){
                String message = "Username already exists!";
                LoginRegisterResponse loginRegisterResponse = new LoginRegisterResponse(406,null,message);
                sendResponse(response,gson,loginRegisterResponse);
            }else{
                account.setPassword(PasswordGenerator.generate(account.getPassword()));
                manager.insertAccount(account);
                String jws = null;
                jws = JwtManager.createJWS(account.getUsername(), manager.getUserId(account.getUsername()),false);
                String message = "Ok";
                LoginRegisterResponse loginRegisterResponse = new LoginRegisterResponse(200,jws,message);
                sendResponse(response,gson,loginRegisterResponse);
            }
        }
    }
    private void sendResponse(HttpServletResponse response,Gson gson,LoginRegisterResponse response1){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String jsonInString = gson.toJson(response1);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonInString);
            out.flush();
            throw new IOException();
        } catch (IOException e) {
        }

    }

}
