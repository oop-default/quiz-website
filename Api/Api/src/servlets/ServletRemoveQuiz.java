package servlets;

import database.DatabaseManager;
import parsers.AuthenticationService;
import parsers.QuizParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletRemoveQuiz")
public class ServletRemoveQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("remove Quiz");
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        String quizIdString = request.getParameter("quizId");
        int quizId = Integer.parseInt(quizIdString);
        System.out.println(quizId);
        System.out.println("service: "+service.getUserName());
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return;
        }
        if(service.isAdmin()){
            manager.removeQuiz(quizId);
            System.out.println("removed");
        }else{
            response.setStatus(403);
        }
    }
}
