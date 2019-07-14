package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Account;
import models.Quiz;
import models.SubmittedQuiz;
import parsers.AuthenticationService;
import parsers.QuizParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet(name = "ServletQuiz")
public class ServletQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String token = request.getHeader("Authorization");
//        System.out.println(token);
//        AuthenticationService service = new AuthenticationService(token);
//        if(!service.isAuthenticated()){
//            response.setStatus(401);
//        }
        String quizId = request.getParameter("id");
        int id = Integer.parseInt(quizId);
        System.out.println("get Quiz: " + id);
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        Quiz quiz = QuizParser.getQuiz(id,manager);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if(quiz!=null){
            PrintWriter writer = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String quizToJson = gson.toJson(quiz,Quiz.class);
            writer.print(quizToJson);
        }else{
            response.setStatus(404);
        }

    }
}
