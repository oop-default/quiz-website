package servlets;

import com.google.gson.Gson;
import database.DatabaseManager;
import models.Announcement;
import models.Quiz;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestServletQuizCategories extends Mockito {
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
    public void testGetMethod1() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);

        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        when(request.getParameter("category")).thenReturn("history");
        ArrayList<Quiz> quizzes = getQuizesByCaategory();
        when(manager.getQuizesByCategory("history")).thenReturn(quizzes);
        Gson gson = new Gson();
        String toString = gson.toJson(quizzes, ArrayList.class);
        ServletQuizCategories myServlet = new ServletQuizCategories();
        myServlet.doGet(request,response);
        String result = stringWriter.toString();
        assertEquals(toString,result);
    }
    @Test
    public void testGetMethod2() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("category")).thenReturn("history");
        ArrayList<Quiz> quizzes = getQuizesByCaategory();
        when(manager.getQuizesByCategory("history")).thenReturn(quizzes);
        Gson gson = new Gson();
        String toString = gson.toJson(quizzes, ArrayList.class);
        ServletQuizCategories myServlet = new ServletQuizCategories();
        myServlet.doGet(request,response);
        String result = stringWriter.toString();
        assertEquals("",result);
    }

    private ArrayList<Quiz> getQuizesByCaategory(){
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setType("history");
        quizzes.add(quiz);
        return quizzes;
    }
}
