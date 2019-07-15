package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Account;
import models.Question;
import models.Quiz;
import org.junit.Test;
import org.mockito.Mockito;
import responseModels.SearchResponse;
import responseModels.quizzesResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestSearch extends Mockito {
    @Test
    public void firstTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("query")).thenReturn("is");
        ServletContext context = mock(ServletContext .class);

        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        ArrayList<Account> accs = new ArrayList<>();
        accs.add(new Account("vigac", "is", "isa", "imishvili", "male"));
        ArrayList<Quiz> quizzes = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        Quiz quiz = new Quiz(1, "is", "iseti", "vigacam ragaca", "vigaca", questions, 50, "maSin");
        SearchResponse search = new SearchResponse(accs, quizzes);
        when(manager.getSearchResponse("is")).thenReturn(search);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletSearch tk = new ServletSearch() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        tk.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(search);

        assertEquals(output, stringWriter.toString());
    }


}
