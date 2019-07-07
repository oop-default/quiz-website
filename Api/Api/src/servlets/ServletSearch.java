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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        DatabaseManager manager = (DatabaseManager)getServletContext().getAttribute("database");
        SearchResponse response1 = new SearchResponse(AccountParser.getAccountsByPatrialMatchName(query,manager),QuizParser.findQuizByTitle(query,manager));
        Gson gson = new Gson();
        String jsonString = gson.toJson(response1,SearchResponse.class);
        writer.print(jsonString);

        System.out.println(query);
    }
}
