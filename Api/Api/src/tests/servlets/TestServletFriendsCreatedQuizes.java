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

public class TestServletFriendsCreatedQuizes extends Mockito {
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

        ArrayList<Quiz> quizzes = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setAuthor("giorgi");
        quiz.setType("geography");
        quiz.setTitle("test");
        quiz.setNum_points(5);
        quiz.setDateCreated("7/13/2019");
        quiz.setDescription("test");
        quizzes.add(quiz);
        when(manager.friendRecentCreatedQuizes(7)).thenReturn(quizzes);
        ServletFriendsCreatedQuizes myServlet = new ServletFriendsCreatedQuizes();
        myServlet.doGet(request,response);
        Gson gson = new Gson();
        String toString = gson.toJson(quizzes,ArrayList.class);
        String result = stringWriter.toString();
        assertEquals(toString,result);

    }
    @Test
    public void testGetMethod2() throws IOException, ServletException {
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("database")).thenReturn(manager);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Quiz quiz = new Quiz();
        quiz.setAuthor("giorgi");
        quiz.setType("geography");
        quiz.setTitle("test");
        quiz.setNum_points(5);
        quiz.setDateCreated("7/13/2019");
        quiz.setDescription("test");
        quizzes.add(quiz);
        when(manager.friendRecentCreatedQuizes(7)).thenReturn(quizzes);
        ServletFriendsCreatedQuizes myServlet = new ServletFriendsCreatedQuizes();
        myServlet.doGet(request,response);
        String result = stringWriter.toString();
        assertEquals("",result);
    }

}
