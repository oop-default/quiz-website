package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import models.Score;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendTopScoresTest {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        DatabaseManager manager = mock(DatabaseManager.class);
        ServletContext context = mock(ServletContext.class);
        when(context.getAttribute("database")).thenReturn(manager);
        when(request.getParameter("quizid")).thenReturn("1");
        when(request.getParameter("id")).thenReturn("1");
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score(1,"me",1000));

        when(manager.getFriendTopScores(1,1)).thenReturn(scores);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        ServletFriendTopScores sgts = new ServletFriendTopScores(){
            public ServletContext getServletContext(){
                return context;
            }
        };
        sgts.doGet(request,response);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String output = gson.toJson(scores,ArrayList.class);

        assertEquals(output,stringWriter.toString());
    }
}
