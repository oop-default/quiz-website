package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Quiz;
import parsers.AccountParser;
import parsers.QuizParser;
import responseModels.SearchResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletSearch")
public class ServletSearch extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        if(query.equals("")){
            response.setStatus(404);
            return;
        }
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        DatabaseManager manager = (DatabaseManager)request.getServletContext().getAttribute("database");
        SearchResponse response1 = new SearchResponse(manager.getAccountsByPatrialMatchName(query),
               manager.findQuizByTitle(query));
        Gson gson = new Gson();
        String jsonString = gson.toJson(response1,SearchResponse.class);
        writer.print(jsonString);

        System.out.println(query);
    }
}
