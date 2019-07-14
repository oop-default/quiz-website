package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import org.junit.Test;
import org.mockito.Mockito;
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

public class testRecQuizzes extends Mockito {
    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ServletContext context = mock(ServletContext .class);

        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        List<quizzesResponse> recQuizzes = new ArrayList<>();
        quizzesResponse quiz = new quizzesResponse(1, "ragaca");
        recQuizzes.add(quiz);
        when(manager.getRecQuizzes()).thenReturn(recQuizzes);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletRecentQuizzes rec = new ServletRecentQuizzes() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        rec.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(recQuizzes);

        assertEquals(output, stringWriter.toString());
    }
}
