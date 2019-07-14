package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Quiz;
import models.SubmittedQuiz;
import parsers.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "ServletSaveNewQuiz")
public class ServletSaveNewQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");

//        String token = request.getHeader("Authorization");
//        AuthenticationService service = manager.getService(token);
//        if(!service.isAuthenticated()){
//            response.setStatus(401);
//            return;
//        }
//        String author = service.getUserName();
        String author = "vaxushti";
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Quiz nq = gson.fromJson(reader, Quiz.class);
        nq.setAuthor(author);
        boolean inserted = manager.saveNewQuiz(nq);
        if(inserted){
            response.setStatus(200);
        }else{
            response.setStatus(500);
        }
    }
}
