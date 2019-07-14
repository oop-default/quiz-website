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
import java.sql.SQLException;

@WebServlet(name = "ServletClearQuizHistory")
public class ServletClearQuizHistory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizIdString = request.getParameter("quizId");
        int quizId = Integer.parseInt(quizIdString);
        String token = request.getHeader("Authorization");
        AuthenticationService service = new AuthenticationService(token);
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        if(isValid(service,request,response)){
            manager.removeQuizHistory(quizId);
        }
    }
    private boolean isValid(AuthenticationService service,HttpServletRequest request,HttpServletResponse response){
        if(!service.isAuthenticated()){
            response.setStatus(401);
            return false;
        }
        if(!service.isAdmin()){
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
