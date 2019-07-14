package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Account;
import models.Quiz;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import responseModels.SearchResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestServletSearch extends Mockito {

    HttpServletResponse response;
    HttpServletRequest request ;
    ServletContext context ;
    DatabaseManager manager;
    StringWriter stringWriter;
    PrintWriter writer;
    @Before
    public void set(){
        request = mock(HttpServletRequest.class);
        response =mock(HttpServletResponse.class);
        context = mock(ServletContext.class);
        manager = mock(DatabaseManager.class);


        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
    }

    @Test
    public void testGetMethod1() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);

        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";

        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        when(request.getParameter("query")).thenReturn("abc");
        ArrayList<Account> accounts = getAccounts();
        ArrayList<Quiz> quizzes = getQuizzes();
        when(manager.findQuizByTitle("abc")).thenReturn(quizzes);
        when(manager.getAccountsByPatrialMatchName("abc")).thenReturn(accounts);
        SearchResponse searchResponse = new SearchResponse(accounts,quizzes);
        Gson gson = new Gson();
        String toString = gson.toJson(searchResponse, SearchResponse.class);
        ServletSearch myServlet = new ServletSearch();
        myServlet.doGet(request,response);
        String result = stringWriter.toString();
        assertEquals(toString,result);
    }

    @Test

    public void testGetMethod2() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);

        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";

        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        when(request.getParameter("query")).thenReturn("");
        ServletSearch myServlet = new ServletSearch();
        myServlet.doGet(request,response);
        String result = stringWriter.toString();
        assertEquals("",result);

    }

    private ArrayList<Account> getAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        return accounts;
    }

    private ArrayList<Quiz> getQuizzes(){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        return quizzes;
    }

}
