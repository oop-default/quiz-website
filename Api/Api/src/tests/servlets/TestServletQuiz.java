package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
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

public class TestServletQuiz extends Mockito {
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


    /*
        user is authenticated.
     */
    @Test
    public void testGetMethod1() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("id")).thenReturn("1");
        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        Quiz quiz = createQuiz();
        when(manager.getQuiz(1)).thenReturn(quiz);
        ServletQuiz servletQuiz = new ServletQuiz();
        servletQuiz.doGet(request,response);


        Gson gson = new GsonBuilder().create();
        String toString = gson.toJson(quiz, Quiz.class);
        String result = stringWriter.toString();
        Quiz QuizResult = gson.fromJson(result,Quiz.class);
        String res = gson.toJson(QuizResult,Quiz.class);

        assertEquals(toString,res);


    }

    private Quiz createQuiz(){
        Quiz quiz = new Quiz();
        quiz.setDescription("test");
        quiz.setDateCreated("7/13/2019");
        quiz.setType("test");
        return quiz;
    }


    /*
        user isn't authenticated
     */
    @Test
    public void testGetMethod2() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("id")).thenReturn("1");
        Quiz quiz = createQuiz();
        when(manager.getQuiz(1)).thenReturn(quiz);
        ServletQuiz servletQuiz = new ServletQuiz();
        servletQuiz.doGet(request,response);
    }

    /*
        quiz doesnt exists
     */

    @Test
    public void testGetMethod3() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("id")).thenReturn("1");
        String AuthorizationToken = "bearer eyJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOiJqaWtzc2kiLCJ1c2VySWQiOjcsImlzQWRtaW4iOnRydWV9." +
                "lgVtnSXJKQhHZ1SirSlVAk5g4csX5wUkIhzinMIc0PA";
        when(request.getHeader("Authorization")).thenReturn(AuthorizationToken);
        Quiz quiz = createQuiz();
        when(manager.getQuiz(1)).thenReturn(null);
        ServletQuiz servletQuiz = new ServletQuiz();
        servletQuiz.doGet(request,response);
    }
}
