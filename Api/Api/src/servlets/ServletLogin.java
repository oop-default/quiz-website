package servlets;

import Jwt.JwtManager;
import com.google.gson.Gson;
import cracker.PasswordGenerator;
import database.DatabaseManager;
import models.Account;
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

@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Account account = gson.fromJson(reader, Account.class);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");

        String hashedPassword = PasswordGenerator.generate(account.getPassword());
        if(AuthorizationParser.validUser(account.getUsername(),hashedPassword,manager)){
            String jws = JwtManager.createJWS(account.getUsername());
            LoginRegisterResponse response1 = new LoginRegisterResponse(200,jws,"Ok");
            sendResponse(response,gson,response1);
        }else {
            String message = "Username or Password is incorrect. Try Again!";
            LoginRegisterResponse response1 = new LoginRegisterResponse(400,null,message);
            sendResponse(response,gson,response1);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
