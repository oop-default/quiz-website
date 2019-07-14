package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.DatabaseManager;
import org.junit.Test;
import org.mockito.Mockito;
import parsers.AuthenticationService;
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

public class testCreatedQuizzes extends Mockito {

    @Test
    public void firstTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        AuthenticationService service = mock(AuthenticationService.class);
        when(service.isAuthenticated()).thenReturn(true);
        when(service.getUserId()).thenReturn(1);
        when(request.getHeader("Authorization")).thenReturn("ragaca");
        ServletContext context = mock(ServletContext .class);
        DatabaseManager manager = mock(DatabaseManager.class);
        when(context.getAttribute("database")).thenReturn(manager);
        when(manager.getService("ragaca")).thenReturn(service);


        List<quizzesResponse> createdQuizzes = new ArrayList<>();
        quizzesResponse quiz = new quizzesResponse(1, "ragaca");
        createdQuizzes.add(quiz);
        when(manager.getCreatedQuizzes(1)).thenReturn(createdQuizzes);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        ServletCreatedQuizzes cr = new ServletCreatedQuizzes() {
            public ServletContext getServletContext() {
                return context;
            }
        };
        cr.doGet(request, response);


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String output = gson.toJson(createdQuizzes);

        assertEquals(output, stringWriter.toString());
    }
}
